/*
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.huntzou.webfont.tools.subsetter;

import com.huntzou.webfont.sfntly.Font;
import com.huntzou.webfont.sfntly.Font.Builder;
import com.huntzou.webfont.sfntly.Tag;
import com.huntzou.webfont.sfntly.table.core.CMap;
import com.huntzou.webfont.sfntly.table.core.CMapTable;

import java.io.IOException;

/**
 * @author Stuart Gill
 *
 */
public class CMapTableSubsetter extends TableSubsetterImpl {

  /**
   * Constructor.
   */
  public CMapTableSubsetter() {
    super(Tag.cmap);
  }

  @Override
  public boolean subset(Subsetter subsetter, Font font, Builder fontBuilder) throws IOException {
    CMapTable cmapTable = font.getTable(Tag.cmap);
    if (cmapTable == null) {
      throw new RuntimeException("Font to subset is not valid.");
    }

    CMapTable.Builder cmapTableBuilder =
        (com.huntzou.webfont.sfntly.table.core.CMapTable.Builder) fontBuilder
            .newTableBuilder(Tag.cmap);

    for (CMapTable.CMapId cmapId : subsetter.cmapId()) {
      CMap cmap = cmapTable.cmap(cmapId);
      if (cmap != null) {
        cmapTableBuilder.newCMapBuilder(cmapId, cmap.readFontData());
      }
    }
    return true;
  }
}
