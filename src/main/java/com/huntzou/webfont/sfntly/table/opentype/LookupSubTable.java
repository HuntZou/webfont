// Copyright 2012 Google Inc. All Rights Reserved.

package com.huntzou.webfont.sfntly.table.opentype;

import com.huntzou.webfont.sfntly.data.ReadableFontData;
import com.huntzou.webfont.sfntly.table.opentype.component.LookupType;

abstract class LookupSubTable extends OTSubTable {

  protected LookupSubTable(ReadableFontData data, boolean dataIsCanonical) {
    super(data, dataIsCanonical);
  }

  // @Override
  // private abstract Builder<? extends LookupSubTable> builder();

  protected abstract LookupType lookupType();

  abstract static class Builder<T extends LookupSubTable> extends OTSubTable.Builder<T> {

    protected Builder(ReadableFontData data, boolean dataIsCanonical) {
      super(data, dataIsCanonical);
    }

    protected Builder(T table) {
      super(table);
    }

    protected abstract LookupType lookupType();
  }
}
