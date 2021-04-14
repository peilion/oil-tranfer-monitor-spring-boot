package cn.peilion.model.pojos.asset;


import lombok.Data;

/**
 * @author fpl11
 */
@Data

public class AssetGeoStat {

    private String stationName;
    private Integer statu;
    private Integer count;
    private double longitude;
    private double latitude;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssetGeoStat that = (AssetGeoStat) o;

        return stationName != null ? stationName.equals(that.stationName) : that.stationName == null;
    }

    @Override
    public int hashCode() {
        return stationName != null ? stationName.hashCode() : 0;
    }
}
