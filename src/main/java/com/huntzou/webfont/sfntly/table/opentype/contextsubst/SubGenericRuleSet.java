package com.huntzou.webfont.sfntly.table.opentype.contextsubst;

import com.huntzou.webfont.sfntly.data.ReadableFontData;
import com.huntzou.webfont.sfntly.table.opentype.component.OffsetRecordTable;

public abstract class SubGenericRuleSet<T extends DoubleRecordTable> extends OffsetRecordTable<T> {
  protected SubGenericRuleSet(ReadableFontData data, int base, boolean dataIsCanonical) {
    super(data, base, dataIsCanonical);
  }

  @Override
  public int fieldCount() {
    return 0;
  }

  protected abstract static class Builder<T extends SubGenericRuleSet<S>,
      S extends DoubleRecordTable>
      extends OffsetRecordTable.Builder<T, S> {

    protected Builder(ReadableFontData data, boolean dataIsCanonical) {
      super(data, dataIsCanonical);
    }

    protected Builder() {
      super();
    }

    protected Builder(T table) {
      super(table);
    }

    @Override
    protected void initFields() {
    }

    @Override
    protected int fieldCount() {
      return 0;
    }
  }
}
