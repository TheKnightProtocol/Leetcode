class Robot {
private:
    int w, h;
    int x, y;
    int dir;
    int perimeter;
    bool start;

    const int dx[4] = {1, 0, -1, 0};
    const int dy[4] = {0, 1, 0, -1};
    const string dirNames[4] = {"East", "North", "West", "South"};

public:
    Robot(int width, int height) {
        w = width;
        h = height;
        x = 0;
        y = 0;
        dir = 0;
        perimeter = 2 * (w + h - 2);
        start = true;
    }

    void step(int num) {
        if (perimeter == 0) return;

        if (start) {
            start = false;
            if (num % perimeter == 0) {
                dir = 3;
                return;
            }
            num = num % perimeter;
        } else {
            num = num % perimeter;
            if (num == 0) return;
        }

        while (num > 0) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx < 0 || nx >= w || ny < 0 || ny >= h) {
                dir = (dir + 1) % 4;
            } else {
                x = nx;
                y = ny;
                num--;
            }
        }
    }

    vector<int> getPos() {
        return {x, y};
    }

    string getDir() {
        if (x == 0 && y == 0 && start) {
            return "East";
        }
        return dirNames[dir];
    }
};
