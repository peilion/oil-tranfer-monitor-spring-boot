import sys
import bisect
from collections import deque
test_num = int(sys.stdin.readline().strip(' '))

for _ in range(test_num):

    n = int(sys.stdin.readline().strip(' '))
    tables = [int(item) for item in sys.stdin.readline().strip(' ')[:-1]]
    m = int(sys.stdin.readline().strip(' '))
    zero_set = deque()
    one_set = deque()

    for i in range(n):
        if tables[i] == 0:
            zero_set.append(i)
        if tables[i] == 1:
            one_set.append(i)


    sex = sys.stdin.readline().strip(' ')
    for people in sex:
        if people == 'M':
            if len(one_set) != 0:
                table =  one_set.popleft()
                print(table+1)
            else:
                table = zero_set.popleft()
                bisect.insort(one_set,table)
                print(table+1)

        elif people == 'F':
            if len(zero_set) != 0:
                table = zero_set.popleft()
                bisect.insort(one_set,table)
                print(table+1)
            else:
                table = one_set.popleft()
                print(table+1)
from bisect import insort