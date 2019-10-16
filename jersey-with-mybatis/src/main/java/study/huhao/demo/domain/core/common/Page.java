package study.huhao.demo.domain.core.common;

import com.google.common.collect.ImmutableList;
import study.huhao.demo.domain.core.concepts.ReadModel;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Page<T> implements ReadModel {
    private List<T> results;
    private int limit;
    private long offset;
    private long total;

    public Page(List<T> results, int limit, long offset, long total) {
        this.results = results;
        this.limit = limit;
        this.offset = offset;
        this.total = total;
    }

    public List<T> getResults() {
        // return immutable list to follow the encapsulation principle
        return ImmutableList.copyOf(this.results);
    }

    public int getLimit() {
        return limit;
    }

    public long getOffset() {
        return offset;
    }

    public long getTotal() {
        return total;
    }

    public <U> Page<U> map(Function<? super T, ? extends U> converter) {
        return new Page<>(getConvertedContent(converter), limit, offset, total);
    }

    private <U> List<U> getConvertedContent(Function<? super T, ? extends U> converter) {

        if (converter == null) throw new IllegalArgumentException("function must not be null");

        return this.results.stream().map(converter).collect(Collectors.toList());
    }
}
