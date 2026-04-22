class Solution {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> res = new ArrayList<>();
        for (String q : queries) {
            for (String d : dictionary) {
                int diff = 0;
                for (int i = 0; i < q.length() && diff <= 2; i++)
                    if (q.charAt(i) != d.charAt(i)) diff++;
                if (diff <= 2) {
                    res.add(q);
                    break;
                }
            }
        }
        return res;
    }
}
