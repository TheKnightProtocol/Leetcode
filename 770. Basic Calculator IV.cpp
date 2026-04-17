class Solution {
    struct Poly {
        map<vector<string>, int> terms;
        Poly() {}
        Poly(int c) { if (c) terms[{}] = c; }
        Poly(const string& var) { terms[{var}] = 1; }

        Poly operator+(const Poly& o) const {
            Poly res = *this;
            for (auto& [vars, coeff] : o.terms)
                res.terms[vars] += coeff;
            res.simplify();
            return res;
        }
        Poly operator-(const Poly& o) const {
            Poly res = *this;
            for (auto& [vars, coeff] : o.terms)
                res.terms[vars] -= coeff;
            res.simplify();
            return res;
        }
        Poly operator*(const Poly& o) const {
            Poly res;
            for (auto& [v1, c1] : terms) {
                for (auto& [v2, c2] : o.terms) {
                    vector<string> vars;
                    merge(v1.begin(), v1.end(), v2.begin(), v2.end(), back_inserter(vars));
                    res.terms[vars] += c1 * c2;
                }
            }
            res.simplify();
            return res;
        }
        void simplify() {
            for (auto it = terms.begin(); it != terms.end(); ) {
                if (it->second == 0) it = terms.erase(it);
                else ++it;
            }
        }
    };

    vector<string> tokenize(const string& s) {
        vector<string> tokens;
        int n = s.size();
        for (int i = 0; i < n; ) {
            if (s[i] == ' ') { ++i; continue; }
            if (s[i] == '(' || s[i] == ')' || s[i] == '+' || s[i] == '-' || s[i] == '*') {
                tokens.push_back(string(1, s[i]));
                ++i;
                continue;
            }
            if (isdigit(s[i])) {
                int j = i;
                while (j < n && isdigit(s[j])) ++j;
                tokens.push_back(s.substr(i, j - i));
                i = j;
                continue;
            }
            if (isalpha(s[i])) {
                int j = i;
                while (j < n && isalpha(s[j])) ++j;
                tokens.push_back(s.substr(i, j - i));
                i = j;
                continue;
            }
            ++i;
        }
        return tokens;
    }

    Poly parseExpr(const vector<string>& tok, int& i, const unordered_map<string, int>& eval);
    Poly parseTerm(const vector<string>& tok, int& i, const unordered_map<string, int>& eval);
    Poly parseFactor(const vector<string>& tok, int& i, const unordered_map<string, int>& eval);

public:
    vector<string> basicCalculatorIV(string expr, vector<string>& vars, vector<int>& vals) {
        unordered_map<string, int> eval;
        for (int i = 0; i < vars.size(); ++i) eval[vars[i]] = vals[i];
        auto tokens = tokenize(expr);
        int idx = 0;
        Poly res = parseExpr(tokens, idx, eval);
        vector<pair<vector<string>, int>> vec(res.terms.begin(), res.terms.end());
        sort(vec.begin(), vec.end(),
             [](const auto& a, const auto& b) {
                 if (a.first.size() != b.first.size())
                     return a.first.size() > b.first.size();
                 return a.first < b.first;
             });
        vector<string> ans;
        for (auto& [varsList, coeff] : vec) {
            string term = to_string(coeff);
            if (!varsList.empty()) {
                term += "*";
                for (size_t j = 0; j < varsList.size(); ++j) {
                    if (j) term += "*";
                    term += varsList[j];
                }
            }
            ans.push_back(term);
        }
        return ans;
    }
};

Solution::Poly Solution::parseExpr(const vector<string>& tok, int& i, const unordered_map<string, int>& eval) {
    Poly res = parseTerm(tok, i, eval);
    while (i < tok.size() && (tok[i] == "+" || tok[i] == "-")) {
        string op = tok[i++];
        Poly term = parseTerm(tok, i, eval);
        if (op == "+") res = res + term;
        else res = res - term;
    }
    return res;
}

Solution::Poly Solution::parseTerm(const vector<string>& tok, int& i, const unordered_map<string, int>& eval) {
    Poly res = parseFactor(tok, i, eval);
    while (i < tok.size() && tok[i] == "*") {
        ++i;
        Poly f = parseFactor(tok, i, eval);
        res = res * f;
    }
    return res;
}

Solution::Poly Solution::parseFactor(const vector<string>& tok, int& i, const unordered_map<string, int>& eval) {
    string cur = tok[i];
    if (cur == "(") {
        ++i;
        Poly e = parseExpr(tok, i, eval);
        ++i;
        return e;
    }
    if (isdigit(cur[0])) {
        ++i;
        return Poly(stoi(cur));
    }
    ++i;
    auto it = eval.find(cur);
    if (it != eval.end()) return Poly(it->second);
    return Poly(cur);
}
