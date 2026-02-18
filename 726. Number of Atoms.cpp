class Solution {
public:
    string countOfAtoms(string formula) {
        int i = 0;
        map<string, int> counts = parseFormula(formula, i);
        
        string result;
        for (auto& [atom, count] : counts) {
            result += atom;
            if (count > 1) {
                result += to_string(count);
            }
        }
        return result;
    }
    
private:
    map<string, int> parseFormula(string& formula, int& i) {
        map<string, int> counts;
        int n = formula.length();
        
        while (i < n && formula[i] != ')') {
            if (formula[i] == '(') {
                i++;
                map<string, int> innerCounts = parseFormula(formula, i);
                i++;
                
                int multiplier = parseNumber(formula, i);
                for (auto& [atom, count] : innerCounts) {
                    counts[atom] += count * multiplier;
                }
            } else {
                string atom = parseAtom(formula, i);
                int count = parseNumber(formula, i);
                counts[atom] += count;
            }
        }
        
        return counts;
    }
    
    string parseAtom(string& formula, int& i) {
        string atom;
        atom += formula[i++];
        while (i < formula.length() && islower(formula[i])) {
            atom += formula[i++];
        }
        return atom;
    }
    
    int parseNumber(string& formula, int& i) {
        if (i >= formula.length() || !isdigit(formula[i])) {
            return 1;
        }
        
        int num = 0;
        while (i < formula.length() && isdigit(formula[i])) {
            num = num * 10 + (formula[i++] - '0');
        }
        return num;
    }
};
