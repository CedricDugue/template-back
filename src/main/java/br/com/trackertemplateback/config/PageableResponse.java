package br.com.trackertemplateback.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Modelo generico para consulta retornando varias entidades.
 *
 * @author Cedric Christian on 30/04/2021
 */
public class PageableResponse<T> extends PageImpl<T> {

    private boolean first;
    private boolean last;
    private int totalPages;
    private int size;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PageableResponse(@JsonProperty("content") List<T> content,
                            @JsonProperty("pageable") JsonNode pageable,
                            @JsonProperty("sort") JsonNode sort,
                            @JsonProperty("totalElements") long totalElements,
                            @JsonProperty("size") int size,
                            @JsonProperty("number") int page,
                            @JsonProperty("numberOfElements") int numberOfElements) {

        super(content, PageRequest.of(page, size, Sort.by(String.valueOf(sort))), totalElements);

        this.size = size;
    }

    public PageableResponse(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public PageableResponse(List<T> content) {
        super(content);
    }

    public PageableResponse() {
        super(new ArrayList<>());
    }

    @Override
    public boolean isFirst() {
        return first;
    }

    @Override
    public boolean isLast() {
        return last;
    }

    @Override
    public int getTotalPages() {
        return totalPages;
    }

    @Override
    public int getSize() {
        return size;
    }
}
