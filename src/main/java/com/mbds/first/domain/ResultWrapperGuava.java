package com.mbds.first.domain;

import com.google.common.base.Objects;
import jersey.repackaged.com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by marwen on 15/12/15.
 */
public class ResultWrapperGuava {
    private final List<String> result= Lists.newArrayList();

    public ResultWrapperGuava(String... result) {
        this.result.addAll(Lists.newArrayList(result));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultWrapperGuava that = (ResultWrapperGuava) o;
        return com.google.common.base.Objects.equal(result, that.result);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(result);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("result", result)
                .toString();
    }
}
