class Solution {
private:
    vector<unordered_map<string, int>> scopes;
    int i = 0;
    string expr;
    
    int getNumber() {
        int sign = 1;
        if (expr[i] == '-') {
            sign = -1;
            i++;
        }
        
        int num = 0;
        while (i < expr.length() && isdigit(expr[i])) {
            num = num * 10 + (expr[i] - '0');
            i++;
        }
        return sign * num;
    }
    
    string getVariable() {
        string var;
        while (i < expr.length() && expr[i] != ' ' && expr[i] != ')') {
            var += expr[i];
            i++;
        }
        return var;
    }
    
    int evaluate() {
        if (expr[i] == '(') {
            i++; // skip '('
            
            string token = getVariable();
            int result;
            
            if (token == "let") {
                i++; // skip space
                unordered_map<string, int> newScope;
                scopes.push_back(newScope);
                
                while (true) {
                    if (expr[i] == '(' || isdigit(expr[i]) || expr[i] == '-') {
                        result = evaluate();
                        break;
                    } else {
                        string var = getVariable();
                        if (expr[i] == ')') {
                            result = getValue(var);
                            break;
                        }
                        i++; // skip space
                        int val = evaluate();
                        scopes.back()[var] = val;
                        if (expr[i] == ' ') i++;
                    }
                }
                
                scopes.pop_back();
                i++; // skip ')'
                return result;
            } 
            else if (token == "add" || token == "mult") {
                i++; // skip space
                int e1 = evaluate();
                i++; // skip space
                int e2 = evaluate();
                i++; // skip ')'
                
                if (token == "add") {
                    return e1 + e2;
                } else {
                    return e1 * e2;
                }
            }
        } 
        else if (isdigit(expr[i]) || expr[i] == '-') {
            return getNumber();
        } 
        else {
            string var = getVariable();
            return getValue(var);
        }
        
        return 0;
    }
    
    int getValue(string var) {
        for (int j = scopes.size() - 1; j >= 0; j--) {
            if (scopes[j].count(var)) {
                return scopes[j][var];
            }
        }
        return 0;
    }
    
public:
    int evaluate(string expression) {
        expr = expression;
        i = 0;
        return evaluate();
    }
};
