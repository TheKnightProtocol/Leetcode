import java.util.*;

class Solution {
    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        List<String> valid = new ArrayList<>();
        
        for (int i = 0; i < code.length; i++) {
            if (!isValidCode(code[i])) continue;
            if (!isValidBusinessLine(businessLine[i])) continue;
            if (!isActive[i]) continue;
            
            valid.add(code[i] + "|" + businessLine[i]);
        }
        
        Map<String, Integer> order = new HashMap<>();
        order.put("electronics", 0);
        order.put("grocery", 1);
        order.put("pharmacy", 2);
        order.put("restaurant", 3);
        
        Collections.sort(valid, (a, b) -> {
            String[] partsA = a.split("\\|");
            String[] partsB = b.split("\\|");
            
            String codeA = partsA[0];
            String businessA = partsA[1];
            String codeB = partsB[0];
            String businessB = partsB[1];
            
            int orderCompare = Integer.compare(order.get(businessA), order.get(businessB));
            if (orderCompare != 0) return orderCompare;
            
            return codeA.compareTo(codeB);
        });
        
        List<String> result = new ArrayList<>();
        for (String item : valid) {
            result.add(item.split("\\|")[0]);
        }
        
        return result;
    }
    
    private boolean isValidCode(String code) {
        if (code == null || code.isEmpty()) return false;
        for (char c : code.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != '_') {
                return false;
            }
        }
        return true;
    }
    
    private boolean isValidBusinessLine(String business) {
        return business.equals("electronics") ||
               business.equals("grocery") ||
               business.equals("pharmacy") ||
               business.equals("restaurant");
    }
}
