package by.betrayal.personalservice.utils;

import by.betrayal.personalservice.exception.BadRequestException;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;

@EqualsAndHashCode
public class PageUtils implements Pageable {

    private final int limit;
    private final int offset;

    private final Sort sort;


    public PageUtils(int limit, int page) {
        if(limit < 1) {
            throw new BadRequestException();
        }

        if (page < 0) {
            throw new BadRequestException();
        }

        this.limit = limit;
        this.offset = limit * page;
        sort = Sort.by(Sort.Direction.ASC, "id");
    }

    @Override
    public int getPageNumber() {
        return offset / limit;
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new PageUtils(getPageSize(), (int) (getOffset() + getPageSize()));
    }

    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    @Override
    public Pageable first() {
        return new PageUtils(getPageSize(), 0);
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return new PageUtils(getPageSize(), pageNumber * getPageSize());
    }

    @Override
    public boolean hasPrevious() {
        return offset > limit;
    }

    private Pageable previous() {
        if(hasPrevious()) {
            return new PageUtils(getPageSize(), (int) (getOffset() - getPageSize()));
        }

        return this;
    }
}
