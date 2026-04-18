class Solution {
public:
    unordered_map<string, string> urlMap;
    const string base = "http://tinyurl.com/";

    string encode(string longUrl) {
        string shortUrl = base + to_string(urlMap.size());
        urlMap[shortUrl] = longUrl;
        return shortUrl;
    }

    string decode(string shortUrl) {
        return urlMap[shortUrl];
    }
};
