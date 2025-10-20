package io.tiklab.sward.document.export.model.word;

import java.util.List;

public class WordTable {

    private List<List<Object>> rows;

    public WordTable() {
    }

    public WordTable(List<List<Object>> rows) {
        this.rows = rows;
    }

    public List<List<Object>> getRows() {
        return rows;
    }

    public void setRows(List<List<Object>> rows) {
        this.rows = rows;
    }
}
