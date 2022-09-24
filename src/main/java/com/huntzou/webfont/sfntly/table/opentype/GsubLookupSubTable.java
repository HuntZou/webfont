// Copyright 2012 Google Inc. All Rights Reserved.

package com.huntzou.webfont.sfntly.table.opentype;

import com.huntzou.webfont.sfntly.data.ReadableFontData;
import com.huntzou.webfont.sfntly.table.opentype.component.GsubLookupType;

/**
 * @author dougfelt@google.com (Doug Felt)
 */
abstract class GsubLookupSubTable extends LookupSubTable {

  protected GsubLookupSubTable(ReadableFontData data, boolean dataIsCanonical) {
    super(data, dataIsCanonical);
  }

  @Override
  public abstract Builder<? extends GsubLookupSubTable> builder();

  @Override
  public abstract GsubLookupType lookupType();

  static abstract class Builder<T extends GsubLookupSubTable>
      extends LookupSubTable.Builder<T> {

    protected Builder(ReadableFontData data, boolean dataIsCanonical) {
      super(data, dataIsCanonical);
    }

    protected Builder(T table) {
      super(table);
    }

    @Override
    public abstract GsubLookupType lookupType();
  }
}
