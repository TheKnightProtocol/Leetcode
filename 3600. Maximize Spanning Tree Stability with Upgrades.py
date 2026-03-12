class Solution:
    def maxStability(self, n, edges, k):
        parent = list(range(n))
        
        def find(p, i):
            if p[i] == i:
                return i
            p[i] = find(p, p[i])
            return p[i]

        def union(p, i, j):
            root_i = find(p, i)
            root_j = find(p, j)
            if root_i != root_j:
                p[root_i] = root_j
                return True
            return False

        mandatory_indices = []
        for i, (u, v, s, must) in enumerate(edges):
            if must == 1:
                if not union(parent, u, v):
                    return -1
                mandatory_indices.append(i)
        
        possible_strengths = set()
        for u, v, s, must in edges:
            possible_strengths.add(s)
            if must == 0:
                possible_strengths.add(s * 2)
        
        sorted_strengths = sorted(list(possible_strengths))

        def can_achieve(target):
            curr_parent = list(range(n))
            components = n
            
            for idx in mandatory_indices:
                u, v, s, _ = edges[idx]
                if s < target:
                    return False
                if union(curr_parent, u, v):
                    components -= 1

            for u, v, s, must in edges:
                if must == 0 and s >= target:
                    if union(curr_parent, u, v):
                        components -= 1

            upgrades_used = 0
            for u, v, s, must in edges:
                if must == 0 and s < target and s * 2 >= target:
                    if union(curr_parent, u, v):
                        upgrades_used += 1
                        components -= 1
            
            return components == 1 and upgrades_used <= k

        ans = -1
        low, high = 0, len(sorted_strengths) - 1
        
        while low <= high:
            mid = (low + high) // 2
            if can_achieve(sorted_strengths[mid]):
                ans = sorted_strengths[mid]
                low = mid + 1
            else:
                high = mid - 1
                
        return ans
