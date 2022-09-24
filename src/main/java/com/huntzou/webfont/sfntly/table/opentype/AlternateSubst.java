package com.huntzou.webfont.sfntly.table.opentype;

import com.huntzou.webfont.sfntly.data.ReadableFontData;
import com.huntzou.webfont.sfntly.table.opentype.component.OneToManySubst;

public class AlternateSubst extends OneToManySubst {
  AlternateSubst(ReadableFontData data, int base, boolean dataIsCanonical) {
    super(data, base, dataIsCanonical);
  }
}
