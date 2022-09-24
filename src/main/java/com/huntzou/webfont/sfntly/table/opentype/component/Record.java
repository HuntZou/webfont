package com.huntzou.webfont.sfntly.table.opentype.component;

import com.huntzou.webfont.sfntly.data.WritableFontData;

interface Record {
  int writeTo(WritableFontData newData, int base);
}
