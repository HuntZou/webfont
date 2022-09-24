package com.huntzou.webfont.sfntly.table.opentype.chaincontextsubst;

import com.huntzou.webfont.sfntly.data.ReadableFontData;

public class ChainSubRule extends ChainSubGenericRule {
  ChainSubRule(ReadableFontData data, int base, boolean dataIsCanonical) {
    super(data, base, dataIsCanonical);
  }

  static class Builder extends ChainSubGenericRule.Builder<ChainSubRule> {
    Builder() {
      super();
    }

    Builder(ChainSubRule table) {
      super(table);
    }

    Builder(ReadableFontData data, int base, boolean dataIsCanonical) {
      super(data, base, dataIsCanonical);
    }

    @Override
    public ChainSubRule subBuildTable(ReadableFontData data) {
      return new ChainSubRule(data, 0, true);
    }
  }
}
