package com.huntzou.webfont.sfntly.table.opentype.component;

import com.huntzou.webfont.sfntly.data.ReadableFontData;
import com.huntzou.webfont.sfntly.data.WritableFontData;

public final class RangeRecordList extends RecordList<RangeRecord> {
  public RangeRecordList(WritableFontData data) {
    super(data);
  }

  public RangeRecordList(ReadableFontData data) {
    super(data);
  }

  public static int sizeOfListOfCount(int count) {
    return RecordList.DATA_OFFSET + count * RangeRecord.RECORD_SIZE;
  }

  @Override
  protected RangeRecord getRecordAt(ReadableFontData data, int offset) {
    return new RangeRecord(data, offset);
  }

  @Override
  protected int recordSize() {
    return RangeRecord.RECORD_SIZE;
  }
}
