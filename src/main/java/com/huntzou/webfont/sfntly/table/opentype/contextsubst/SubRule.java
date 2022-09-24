package com.huntzou.webfont.sfntly.table.opentype.contextsubst;

import com.huntzou.webfont.sfntly.data.ReadableFontData;

public class SubRule extends DoubleRecordTable {
  SubRule(ReadableFontData data, int base, boolean dataIsCanonical) {
    super(data, base, dataIsCanonical);
  }

  static class Builder extends DoubleRecordTable.Builder<SubRule> {
    Builder() {
      super();
    }

    Builder(SubRule table) {
      super(table);
    }

    Builder(ReadableFontData data, int base, boolean dataIsCanonical) {
      super(data, base, dataIsCanonical);
    }

    @Override
    protected SubRule subBuildTable(ReadableFontData data) {
      return new SubRule(data, 0, true);
    }
  }
}
