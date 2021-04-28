#! /usr/bin/env python
# -*- coding: utf-8 -*-
# vim:fenc=utf-8
#
# Copyright © 2015 jaidev <jaidev@newton>
#
# Distributed under terms of the MIT license.

"""Empirical Mode Decomposition."""

import warnings

import numpy as np
from numpy import pi
from scipy import interpolate, angle
from scipy.interpolate import splrep, splev
from scipy.signal import argrelmax, argrelmin


def inst_freq(x, t=None):
    """
    Compute the instantaneous frequency of an analytic signal at specific time
    instants using the trapezoidal integration rule.
    Parameters
    ----------
    x : array-like, shape (n_samples,)
        The input analytic signal.
    t : array-like, shape (n_samples,), optional
        The time instants at which to calculate the instantaneous frequency.
        Defaults to `np.arange(2, n_samples)`
    Returns
    -------
    array-like
        Normalized instantaneous frequencies of the input signal
    Examples
    --------
    """
    if x.ndim != 1:
        if 1 not in x.shape:
            raise TypeError("Input should be a one dimensional array.")
        else:
            x = x.ravel()
    if t is not None:
        if t.ndim != 1:
            if 1 not in t.shape:
                raise TypeError("Time instants should be a one dimensional " "array.")
            else:
                t = t.ravel()
    else:
        t = np.arange(2, len(x))

    fnorm = 0.5 * (angle(-x[t] * np.conj(x[t - 2])) + np.pi) / (2 * np.pi)
    return fnorm, t


def boundary_conditions(signal, time_samples, z=None, nbsym=2):
    """
    Extend a 1D signal by mirroring its extrema on either side.
    Parameters
    ----------
    signal : array-like, shape (n_samples,)
        The input signal.
    time_samples : array-like, shape (n_samples,)
        Timestamps of the signal samples
    z : array-like, shape (n_samples,), optional
        A proxy signal on whose extrema the interpolation is evaluated.
        Defaults to `signal`.
    nbsym : int, optional
        The number of extrema to consider on either side of the signal.
        Defaults to 2
    Returns
    -------
    tuple
        A tuple of four arrays which represent timestamps of the minima of the
        extended signal, timestamps of the maxima of the extended signal,
        minima of the extended signal and maxima of the extended signal.
        signal, minima of the extended signal and maxima of the extended
        signal.
    Examples
    --------
    >>> from __future__ import print_function
    >>> import numpy as np
    >>> signal = np.array([-1, 1, -1, 1, -1])
    >>> tmin, tmax, vmin, vmax = boundary_conditions(signal, np.arange(5))
    >>> tmin
    array([-2,  2,  6])
    >>> tmax
    array([-3, -1,  1,  3,  5,  7])
    >>> vmin
    array([-1, -1, -1])
    >>> vmax
    array([1, 1, 1, 1, 1, 1])
    """
    tmax = argrelmax(signal)[0]
    maxima = signal[tmax]
    tmin = argrelmin(signal)[0]
    minima = signal[tmin]

    if tmin.shape[0] + tmax.shape[0] < 3:
        raise ValueError("Not enough extrema.")

    loffset_max = time_samples[tmax[:nbsym]] - time_samples[0]
    roffset_max = time_samples[-1] - time_samples[tmax[-nbsym:]]
    new_tmax = np.r_[
        time_samples[0] - loffset_max[::-1],
        time_samples[tmax],
        roffset_max[::-1] + time_samples[-1],
    ]
    new_vmax = np.r_[maxima[:nbsym][::-1], maxima, maxima[-nbsym:][::-1]]

    loffset_min = time_samples[tmin[:nbsym]] - time_samples[0]
    roffset_min = time_samples[-1] - time_samples[tmin[-nbsym:]]

    new_tmin = np.r_[
        time_samples[0] - loffset_min[::-1],
        time_samples[tmin],
        roffset_min[::-1] + time_samples[-1],
    ]
    new_vmin = np.r_[minima[:nbsym][::-1], minima, minima[-nbsym:][::-1]]
    return new_tmin, new_tmax, new_vmin, new_vmax


def get_envelops(x, t=None):
    """
    Get the upper and lower envelopes of an array, as defined by its extrema.
    Parameters
    ----------
    x : array-like, shape (n_samples,)
        The input array.
    t : array-like, shape (n_samples,), optional
        Timestamps of the signal. Defaults to `np.arange(n_samples,)`
    Returns
    -------
    tuple
        A tuple of arrays representing the upper and the lower envelopes
        respectively.
    Examples
    --------
    >>> import numpy as np
    >>> x = np.random.rand(100,)
    >>> upper, lower = get_envelops(x)
    """
    if t is None:
        t = np.arange(x.shape[0])
    maxima = argrelmax(x)[0]
    minima = argrelmin(x)[0]

    # consider the start and end to be extrema

    ext_maxima = np.zeros((maxima.shape[0] + 2,), dtype=int)
    ext_maxima[1:-1] = maxima
    ext_maxima[0] = 0
    ext_maxima[-1] = t.shape[0] - 1

    ext_minima = np.zeros((minima.shape[0] + 2,), dtype=int)
    ext_minima[1:-1] = minima
    ext_minima[0] = 0
    ext_minima[-1] = t.shape[0] - 1

    tck = interpolate.splrep(t[ext_maxima], x[ext_maxima])
    upper = interpolate.splev(t, tck)
    tck = interpolate.splrep(t[ext_minima], x[ext_minima])
    lower = interpolate.splev(t, tck)
    return upper, lower


def extr(x):
    """
    Extract the indices of the extrema and zero crossings.
    Parameters
    ----------
    x : array-like, shape (n_samples,)
        Input signal.
    Returns
    -------
    tuple
        A tuple of three arrays representing the minima, maxima and zero
        crossings of the signal respectively.
    Examples
    --------
    """
    m = x.shape[0]

    x1 = x[: m - 1]
    x2 = x[1:m]
    indzer = np.where(x1 * x2 < 0)[0]
    if np.any(x == 0):
        iz = np.where(x == 0)[0]
        indz = []
        if np.any(np.diff(iz) == 1):
            zer = x == 0
            dz = np.diff(np.r_[0, zer, 0])
            debz = np.where(dz == 1)[0]
            finz = np.where(dz == -1)[0] - 1
            indz = np.round((debz + finz) / 2)
        else:
            indz = iz
        indzer = np.sort(np.hstack([indzer, indz]))

    indmax = argrelmax(x)[0]
    indmin = argrelmin(x)[0]

    return indmin, indmax, indzer


class EmpiricalModeDecomposition(object):
    """The EMD class."""

    def __init__(
        self,
        x,
        t=None,
        threshold_1=0.05,
        threshold_2=0.5,
        alpha=0.05,
        ndirs=4,
        fixe=0,
        maxiter=2000,
        fixe_h=0,
        n_imfs=0,
        nbsym=2,
        bivariate_mode="bbox_center",
    ):
        """Empirical mode decomposition.
        Parameters
        ----------
        x : array-like, shape (n_samples,)
            The signal on which to perform EMD
        t : array-like, shape (n_samples,), optional
            The timestamps of the signal.
        threshold_1 : float, optional
            Threshold for the stopping criterion, corresponding to
            :math:`\\theta_{1}` in [3]. Defaults to 0.05.
        threshold_2 : float, optional
            Threshold for the stopping criterion, corresponding to
            :math:`\\theta_{2}` in [3]. Defaults to 0.5.
        alpha : float, optional
            Tolerance for the stopping criterion, corresponding to
            :math:`\\alpha` in [3]. Defaults to 0.05.
        ndirs : int, optional
            Number of directions in which interpolants for envelopes are
            computed for bivariate EMD. Defaults to 4. This is ignored if the
            signal is real valued.
        fixe : int, optional
            Number of sifting iterations to perform for each IMF. By default,
            the stopping criterion mentioned in [1] is used. If set to a
            positive integer, each mode is either the result of exactly
            `fixe` number of sifting iterations, or until a pure IMF is
            found, whichever is sooner.
        maxiter : int, optional
            Upper limit of the number of sifting iterations for each mode.
            Defaults to 2000.
        n_imfs : int, optional
            Number of IMFs to extract. By default, this is ignored and
            decomposition is continued until a monotonic trend is left in the
            residue.
        nbsym : int, optional
            Number of extrema to use to mirror the signals on each side of
            their boundaries.
        bivariate_mode : str, optional
            The algorithm to be used for bivariate EMD as described in [4].
            Can be one of 'centroid' or 'bbox_center'. This is ignored if the
            signal is real valued.
        Attributes
        ----------
        is_bivariate : bool
            Whether the decomposer performs bivariate EMD. This is
            automatically determined by the input value. This is True if at
            least one non-zero imaginary component is found in the signal.
        nbits : list
            List of number of sifting iterations it took to extract each IMF.
        References
        ----------
        .. [1] Huang H. et al. 1998 'The empirical mode decomposition and the \
                Hilbert spectrum for nonlinear and non-stationary time series \
                analysis.' \
                Procedings of the Royal Society 454, 903-995
        .. [2] Zhao J., Huang D. 2001 'Mirror extending and circular spline \
                function for empirical mode decomposition method'. \
                Journal of Zhejiang University (Science) V.2, No.3, 247-252
        .. [3] Gabriel Rilling, Patrick Flandrin, Paulo Gonçalves, June 2003: \
                'On Empirical Mode Decomposition and its Algorithms',\
                IEEE-EURASIP Workshop on Nonlinear Signal and Image Processing \
                NSIP-03
        .. [4] Gabriel Rilling, Patrick Flandrin, Paulo Gonçalves, \
                Jonathan M. Lilly. Bivariate Empirical Mode Decomposition. \
                10 pages, 3 figures. Submitted to Signal Processing Letters, \
                IEEE. Matlab/C codes and additional .. 2007. <ensl-00137611>
        Examples
        --------
        .. plot:: ../../docs/examples/simple_emd.py
        """

        self.threshold_1 = threshold_1
        self.threshold_2 = threshold_2
        self.alpha = alpha
        self.maxiter = maxiter
        self.fixe_h = fixe_h
        self.ndirs = ndirs
        self.nbit = 0
        self.Nbit = 0
        self.n_imfs = n_imfs
        self.k = 1
        # self.mask = mask
        self.nbsym = nbsym
        self.nbit = 0
        self.NbIt = 0

        if x.ndim > 1:
            if 1 not in x.shape:
                raise ValueError("x must have only one row or one column.")
        if x.shape[0] > 1:
            x = x.ravel()
        if np.any(np.isinf(x)):
            raise ValueError("All elements of x must be finite.")
        self.x = x
        self.ner = self.nzr = len(self.x)
        self.residue = self.x.copy()

        if t is None:
            self.t = np.arange(max(x.shape))
        else:
            if t.shape != self.x.shape:
                raise ValueError("t must have the same dimensions as x.")
            if t.ndim > 1:
                if 1 not in t.shape:
                    raise ValueError("t must have only one column or one row.")
            if not np.all(np.isreal(t)):
                raise TypeError("t must be a real vector.")
            if t.shape[0] > 1:
                t = t.ravel()
            self.t = t

        if fixe:
            self.maxiter = fixe
            if self.fixe_h:
                raise TypeError("Cannot use both fixe and fixe_h modes")
        self.fixe = fixe

        self.is_bivariate = np.any(np.iscomplex(self.x))
        if self.is_bivariate:
            self.bivariate_mode = bivariate_mode

        self.imf = []
        self.nbits = []

        # FIXME: Masking disabled because it seems to be recursive.

    #        if np.any(mask):
    #            if mask.shape != x.shape:
    #                raise TypeError("Masking signal must have the same",
    #                                "dimensions as the input signal x.")
    #            if mask.shape[0]>1:
    #                mask = mask.ravel()
    #            imf1 = emd(x+mask, opts)

    def io(self):
        r"""Compute the index of orthoginality, as defined by:
        .. math::
            \sum_{i,j=1,i\neq j}^{N}\frac{\|C_{i}\overline{C_{j}}\|}{\|x\|^2}
        Where :math:`C_{i}` is the :math:`i` th IMF.
        Returns
        -------
        float
            Index of orthogonality. Lower values are better.
        Examples
        --------
        >>> import numpy as np
        >>> t = np.linspace(0, 1, 1000)
        >>> modes = np.sin(2 * pi * 5 * t) + np.sin(2 * pi * 10 * t)
        >>> x = modes + t
        >>> decomposer = EMD(x)
        >>> imfs = decomposer.decompose()
        >>> print('%.3f' % decomposer.io())
        0.017
        """
        imf = np.array(self.imf)
        dp = np.dot(imf, np.conj(imf).T)
        mask = np.logical_not(np.eye(len(self.imf)))
        s = np.abs(dp[mask]).sum()
        return s / (2 * np.sum(self.x ** 2))

    def stop_EMD(self):
        """Check if there are enough extrema (3) to continue sifting.
        Returns
        -------
        bool
            Whether to stop further cubic spline interpolation for lack of
            local extrema.
        """
        if self.is_bivariate:
            stop = False
            for k in range(self.ndirs):
                phi = k * pi / self.ndirs
                indmin, indmax, _ = extr(np.real(np.exp(1j * phi) * self.residue))
                if len(indmin) + len(indmax) < 3:
                    stop = True
                    break
        else:
            indmin, indmax, _ = extr(self.residue)
            ner = len(indmin) + len(indmax)
            stop = ner < 3
        return stop

    def mean_and_amplitude(self, m):
        """ Compute the mean of the envelopes and the mode amplitudes.
        Parameters
        ----------
        m : array-like, shape (n_samples,)
            The input array or an itermediate value of the sifting process.
        Returns
        -------
        tuple
            A tuple containing the mean of the envelopes, the number of
            extrema, the number of zero crosssing and the estimate of the
            amplitude of themode.
        """
        # FIXME: The spline interpolation may not be identical with the MATLAB
        # implementation. Needs further investigation.
        if self.is_bivariate:
            if self.bivariate_mode == "centroid":
                nem = []
                nzm = []
                envmin = np.zeros((self.ndirs, len(self.t)))
                envmax = np.zeros((self.ndirs, len(self.t)))
                for k in range(self.ndirs):
                    phi = k * pi / self.ndirs
                    y = np.real(np.exp(-1j * phi) * m)
                    indmin, indmax, indzer = extr(y)
                    nem.append(len(indmin) + len(indmax))
                    nzm.append(len(indzer))
                    if self.nbsym:
                        tmin, tmax, zmin, zmax = boundary_conditions(
                            y, self.t, m, self.nbsym
                        )
                    else:
                        tmin = np.r_[self.t[0], self.t[indmin], self.t[-1]]
                        tmax = np.r_[self.t[0], self.t[indmax], self.t[-1]]
                        zmin, zmax = m[tmin], m[tmax]

                    f = splrep(tmin, zmin)
                    spl = splev(self.t, f)
                    envmin[k, :] = spl

                    f = splrep(tmax, zmax)
                    spl = splev(self.t, f)
                    envmax[k, :] = spl

                envmoy = np.mean((envmin + envmax) / 2, axis=0)
                amp = np.mean(abs(envmax - envmin), axis=0) / 2

            elif self.bivariate_mode == "bbox_center":
                nem = []
                nzm = []
                envmin = np.zeros((self.ndirs, len(self.t)), dtype=complex)
                envmax = np.zeros((self.ndirs, len(self.t)), dtype=complex)
                for k in range(self.ndirs):
                    phi = k * pi / self.ndirs
                    y = np.real(np.exp(-1j * phi) * m)
                    indmin, indmax, indzer = extr(y)
                    nem.append(len(indmin) + len(indmax))
                    nzm.append(len(indzer))
                    if self.nbsym:
                        tmin, tmax, zmin, zmax = boundary_conditions(
                            y, self.t, m, self.nbsym
                        )
                    else:
                        tmin = np.r_[self.t[0], self.t[indmin], self.t[-1]]
                        tmax = np.r_[self.t[0], self.t[indmax], self.t[-1]]
                        zmin, zmax = m[tmin], m[tmax]
                    f = splrep(tmin, zmin)
                    spl = splev(self.t, f)
                    envmin[k,] = np.exp(1j * phi) * spl

                    f = splrep(tmax, zmax)
                    spl = splev(self.t, f)
                    envmax[k,] = np.exp(1j * phi) * spl

                envmoy = np.mean((envmin + envmax), axis=0)
                amp = np.mean(abs(envmax - envmin), axis=0) / 2

        else:
            indmin, indmax, indzer = extr(m)
            nem = len(indmin) + len(indmax)
            nzm = len(indzer)
            if self.nbsym:
                tmin, tmax, mmin, mmax = boundary_conditions(m, self.t, m, self.nbsym)
            else:
                tmin = np.r_[self.t[0], self.t[indmin], self.t[-1]]
                tmax = np.r_[self.t[0], self.t[indmax], self.t[-1]]
                mmin, mmax = m[tmin], m[tmax]

            f = splrep(tmin, mmin)
            envmin = splev(self.t, f)

            f = splrep(tmax, mmax)
            envmax = splev(self.t, f)

            envmoy = (envmin + envmax) / 2
            amp = np.abs(envmax - envmin) / 2.0
        if self.is_bivariate:
            nem = np.array(nem)
            nzm = np.array(nzm)

        return envmoy, nem, nzm, amp

    def stop_sifting(self, m):
        """Evaluate the stopping criteria for the current mode.
        Parameters
        ----------
        m : array-like, shape (n_samples,)
            The current mode.
        Returns
        -------
        bool
            Whether to stop sifting. If this evaluates to true, the current
            mode is interpreted as an IMF.
        """
        # FIXME: This method needs a better name.
        if self.fixe:
            (moyenne, _, _, _), stop_sift = self.mean_and_amplitude(m), 0  # NOQA
        elif self.fixe_h:
            stop_count = 0
            try:
                moyenne, nem, nzm = self.mean_and_amplitude(m)[:3]

                if np.all(abs(nzm - nem) > 1):
                    stop = 0
                    stop_count = 0
                else:
                    stop_count += 1
                    stop = stop_count == self.fixe_h
            except:
                moyenne = np.zeros((len(m)))
                stop = 1
            stop_sift = stop
        else:
            try:
                envmoy, nem, nzm, amp = self.mean_and_amplitude(m)
            except TypeError as err:
                if err.args[0] == "m > k must hold":
                    return 1, np.zeros((len(m)))
            except ValueError as err:
                if err.args[0] == "Not enough extrema.":
                    return 1, np.zeros((len(m)))
            sx = np.abs(envmoy) / amp
            stop = not (
                (
                    (np.mean(sx > self.threshold_1) > self.alpha)
                    or np.any(sx > self.threshold_2)
                )
                and np.all(nem > 2)
            )
            if not self.is_bivariate:
                stop = stop and not (np.abs(nzm - nem) > 1)
            stop_sift = stop
            moyenne = envmoy
        return stop_sift, moyenne

    def keep_decomposing(self):
        """Check whether to continue the sifting operation."""
        return not (self.stop_EMD()) and (
            self.k < self.n_imfs + 1 or self.n_imfs == 0
        )  # and \

    # not(np.any(self.mask))

    def decompose(self):
        """Decompose the input signal into IMFs.
        This function does all the heavy lifting required for sifting, and
        should ideally be the only public method of this class.
        Returns
        -------
        imfs : array-like, shape (n_imfs, n_samples)
            A matrix containing one IMF per row.
        Examples
        --------
        """
        while self.keep_decomposing():

            # current mode
            m = self.residue

            # computing mean and stopping criterion
            stop_sift, moyenne = self.stop_sifting(m)

            # in case current mode is small enough to cause spurious extrema
            if np.max(np.abs(m)) < (1e-10) * np.max(np.abs(self.x)):
                if not stop_sift:
                    warnings.warn("EMD Warning: Amplitude too small, stopping.")
                else:
                    print("Force stopping EMD: amplitude too small.")
                return

            # SIFTING LOOP:
            while not (stop_sift) and (self.nbit < self.maxiter):
                # The following should be controlled by a verbosity parameter.
                # if (not(self.is_bivariate) and
                #     (self.nbit > self.maxiter / 5) and
                #     self.nbit % np.floor(self.maxiter / 10) == 0 and
                #     not(self.fixe) and self.nbit > 100):
                #     print("Mode " + str(self.k) +
                #           ", Iteration " + str(self.nbit))
                #     im, iM, _ = extr(m)
                #     print(str(np.sum(m[im] > 0)) + " minima > 0; " +
                #           str(np.sum(m[im] < 0)) + " maxima < 0.")

                # Sifting
                m = m - moyenne

                # Computing mean and stopping criterion
                stop_sift, moyenne = self.stop_sifting(m)

                self.nbit += 1
                self.NbIt += 1

                # This following warning depends on verbosity and needs better
                # handling
                # if not self.fixe and self.nbit > 100(self.nbit ==
                # (self.maxiter - 1)) and not(self.fixe) and (self.nbit > 100):
                #     warnings.warn("Emd:warning, Forced stop of sifting - " +
                #                   "Maximum iteration limit reached.")

            self.imf.append(m)

            self.nbits.append(self.nbit)
            self.nbit = 0
            self.k += 1

            self.residue = self.residue - m
            self.ort = self.io()

        if np.any(self.residue):
            self.imf.append(self.residue)
        return np.array(self.imf)


EMD = EmpiricalModeDecomposition
