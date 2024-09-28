package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * The horizontal composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 */
public class HComp implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The blocks.
   */
  AsciiBlock[] blocks;

  /**
   * How the blocks are aligned.
   */
  VAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a horizontal composition of two blocks.
   *
   * @param alignment
   *   The way in which the blocks should be aligned.
   * @param leftBlock
   *   The block on the left.
   * @param rightBlock
   *   The block on the right.
   */
  public HComp(VAlignment alignment, AsciiBlock leftBlock,
      AsciiBlock rightBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {leftBlock, rightBlock};
  } // HComp(VAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a horizontal composition of multiple blocks.
   *
   * @param alignment
   *   The alignment of the blocks.
   * @param blocksToCompose
   *   The blocks we will be composing.
   */
  public HComp(VAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // HComp(Alignment, AsciiBLOCK[])

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get one row from the block.
   *
   * @param i the number of the row
   *
   * @return row i.
   *
   * @exception Exception
   *   if i is outside the range of valid rows.
   */
  public String row(int i) throws Exception {
    // Finding max height of composition.
    int height = this.height();

    String returnRow = "";
    if (i < 0 || i > height - 1) {
      throw new Exception("Invalid row");
    } else {

      for (AsciiBlock block : blocks) {
        int padding = 0;
        if (align == VAlignment.CENTER){
          padding = (block.height() - height)/2;
        } else if  (align == VAlignment.BOTTOM){
          padding = block.height() - height;
        }

        if (i < padding || i >= (padding + block.height())){
          returnRow += " ".repeat(block.width());
        } else{
          returnRow += block.row(i-padding);
        }
      }
    }
    return returnRow;
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    int maxHeight = blocks[0].height();
    for (int i = 0; i < blocks.length; i++) {
      if(blocks[i].height() > maxHeight) {
        maxHeight = blocks[i].height();
      } // if
    } // for
    return maxHeight;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    int totalWidth = 0;
    for (int i = 0; i < blocks.length; i++) {
      totalWidth += blocks[i].width();
    } // for
    return totalWidth;
  } // width()

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other
   *   The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and
   *    false otherwise.
   */
  public boolean eqv(AsciiBlock other) {
    return (other instanceof Grid) && (this.height() == other.height()) && (this.width() == other.width());
  } // eqv(AsciiBlock)
} // class HComp
