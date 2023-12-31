package gad.simplehash;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class Hashtable<K, V> {
    private List<Pair<K, V>>[] table;

    private int[] a;

    @SuppressWarnings("unchecked")
    public Hashtable(int minSize, int[] a) {
        int smallest = getNextPowerOfTwo(minSize);
        table = (List<Pair<K, V>>[]) new List[smallest];
        this.a = a;
    }

    public List<Pair<K, V>>[] getTable() {
        return table;
    }

    public static int getNextPowerOfTwo(int i) {
        if (i <= 1) return 1;
        for (int n = 2; true; n = n * 2) {
            if (n >= i) return n;
        }
    }

    public static int fastModulo(int i, int divisor) {
        return i % divisor;
    }

    private byte[] bytes(K k) {
        return k.toString().getBytes(StandardCharsets.UTF_8);
    }

    public int h(K k, ModuloHelper mH) {
        byte[] x = bytes(k);
        return 0;
    }

    public void insert(K k, V v, ModuloHelper mH) {
    }

    public boolean remove(K k, ModuloHelper mH) {
        return false;
    }

    public Optional<V> find(K k, ModuloHelper mH) {
        return Optional.empty();
    }

    public List<V> findAll(K k, ModuloHelper mH) {
        return null;
    }

    public Stream<Pair<K, V>> stream() {
        return Stream.of(table).filter(Objects::nonNull).flatMap(List::stream);
    }

    public Stream<K> keys() {
        return stream().map(Pair::one).distinct();
    }

    public Stream<V> values() {
        return stream().map(Pair::two);
    }
}