import java.util.*;

class MovieRentingSystem {
    // Map from (shop, movie) -> price
    private Map<String, Integer> priceMap;
    
    // Map from movie -> unrented copies (TreeSet sorted by price, shop)
    private Map<Integer, TreeSet<Entry>> available;
    
    // TreeSet of rented movies (sorted by price, shop, movie)
    private TreeSet<Entry> rented;

    public MovieRentingSystem(int n, int[][] entries) {
        priceMap = new HashMap<>();
        available = new HashMap<>();
        
        Comparator<Entry> searchComp = (a, b) -> {
            if (a.price != b.price) return a.price - b.price;
            return a.shop - b.shop;
        };
        
        Comparator<Entry> reportComp = (a, b) -> {
            if (a.price != b.price) return a.price - b.price;
            if (a.shop != b.shop) return a.shop - b.shop;
            return a.movie - b.movie;
        };
        
        rented = new TreeSet<>(reportComp);
        
        for (int[] e : entries) {
            int shop = e[0], movie = e[1], price = e[2];
            String key = shop + "#" + movie;
            priceMap.put(key, price);
            
            available.putIfAbsent(movie, new TreeSet<>(searchComp));
            available.get(movie).add(new Entry(shop, movie, price));
        }
    }

    public List<Integer> search(int movie) {
        List<Integer> res = new ArrayList<>();
        if (!available.containsKey(movie)) return res;
        
        Iterator<Entry> it = available.get(movie).iterator();
        int count = 0;
        while (it.hasNext() && count < 5) {
            res.add(it.next().shop);
            count++;
        }
        return res;
    }

    public void rent(int shop, int movie) {
        String key = shop + "#" + movie;
        int price = priceMap.get(key);
        Entry entry = new Entry(shop, movie, price);
        
        available.get(movie).remove(entry);
        rented.add(entry);
    }

    public void drop(int shop, int movie) {
        String key = shop + "#" + movie;
        int price = priceMap.get(key);
        Entry entry = new Entry(shop, movie, price);
        
        rented.remove(entry);
        available.get(movie).add(entry);
    }

    public List<List<Integer>> report() {
        List<List<Integer>> res = new ArrayList<>();
        Iterator<Entry> it = rented.iterator();
        int count = 0;
        while (it.hasNext() && count < 5) {
            Entry e = it.next();
            res.add(Arrays.asList(e.shop, e.movie));
            count++;
        }
        return res;
    }
}

class Entry {
    int shop;
    int movie;
    int price;

    Entry(int shop, int movie, int price) {
        this.shop = shop;
        this.movie = movie;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entry)) return false;
        Entry e = (Entry) o;
        return shop == e.shop && movie == e.movie && price == e.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shop, movie, price);
    }
}
