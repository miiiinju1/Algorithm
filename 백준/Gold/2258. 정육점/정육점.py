from typing import List, Tuple

def solve_meat_problem(N: int, M: int, meats: List[Tuple[int, int]]) -> int:
    meats.sort(key=lambda x: (x[1], -x[0]))  # 가격 오름차순, 무게 내림차순 정렬
    
    weight = 0
    max_sum = 0
    now = 0
    
    i = 0
    for i in range(N):
        if weight >= M:
            break
        weight += meats[i][0]
        
        if meats[i][1] != now:
            now = meats[i][1]
            max_sum = meats[i][1]
        elif meats[i][1] == now:
            max_sum += meats[i][1]
            
    if weight >= M:
        for j in range(i, N):
            if meats[j][1] > now:
                if max_sum > meats[j][1]:
                    max_sum = meats[j][1]
                break
        return max_sum
    else:
        return -1

if __name__ == "__main__":
    N, M = map(int, input().split())
    meats = []
    for _ in range(N):
        weight, price = map(int, input().split())
        meats.append((weight, price))
    
    result = solve_meat_problem(N, M, meats)
    print(result)