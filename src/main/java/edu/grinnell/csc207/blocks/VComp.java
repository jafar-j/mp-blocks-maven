package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * The vertical composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 * @author Your Name Here
 */
public class VComp implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /** The blocks. */
  AsciiBlock[] blocks;

  /** How the blocks are aligned. */
  HAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a vertical composition of two blocks.
   *
   * @param alignment The way in which the blocks should be aligned.
   * @param topBlock The block on the top.
   * @param bottomBlock The block on the bottom.
   */
  public VComp(HAlignment alignment, AsciiBlock topBlock, AsciiBlock bottomBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {topBlock, bottomBlock};
  } // VComp(HAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a vertical composition of multiple blocks.
   *
   * @param alignment The alignment of the blocks.
   * @param blocksToCompose The blocks we will be composing.
   */
  public VComp(HAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // VComp(HAlignment, AsciiBLOCK[])

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get one row from the block.
   *
   * @param i the number of the row
   * @return row i.
   * @exception Exception if i is outside the range of valid rows.
   */
  public String row(int i) throws Exception {
    int height = this.height();
    if (i < 0 || i >= height) {
      throw new Exception("Invalid row.");
    } else {
      int curFloor = 0;
      int curCeiling = 0;
      int prevCeiling = 0;
      for (AsciiBlock block : blocks) {
        curFloor = prevCeiling;
        curCeiling = prevCeiling + block.height();
        if (curFloor <= i && i < curCeiling) {
          int blockIndex = i - curFloor;
          int width = this.width();
          int padding = width - block.width();

          if (align == HAlignment.CENTER) {
            return " ".repeat(padding / 2)
                + block.row(blockIndex)
                + " ".repeat(padding / 2 + (padding % 2));
          } else if (align == HAlignment.LEFT) {
            return block.row(blockIndex) + " ".repeat(padding);
          } else {
            return " ".repeat(padding) + block.row(blockIndex);
          } // end if
        } // end if
        prevCeiling += block.height();
      } // end for
      return "";
    }
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    int height = 0;
    for (AsciiBlock block : blocks) {
      height += block.height();
    } // end for
    return height;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    int width = 0;
    for (AsciiBlock block : blocks) {
      int blockWidth = block.width();
      if (blockWidth > width) {
        width = blockWidth;
      }
    }
    return width;
  } // width()

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other The block to compare to this block.
   * @return true if the two blocks are structurally equivalent and false otherwise.
   */
  public boolean eqv(AsciiBlock other) {
    if (!(other instanceof VComp)) {
      return false;
    } else if (this.blocks.length != ((VComp) other).blocks.length) {
      return false;
    } // if
    for (int i = 0; i < this.blocks.length; i++) {
      if (!(this.blocks[i].eqv(((VComp) other).blocks[i]))) {
        return false;
      } // if
    } // for
    return true;
  } // eqv(AsciiBlock)
} // class VComp
