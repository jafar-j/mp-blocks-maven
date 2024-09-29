package edu.grinnell.csc207.blocks;

/** Generate alphabetically filled block of giver dimensions.*/
public class Alphabetical {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The height of the rectangle.
   */
  int height;

  /**
   * The height of the rectangle.
   */
  int width;

  /** The integer value of the uppercase 'A' character. */
  private final int VALUE_OF_BIG_A = 65;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a rectangle.
   *
   * @param rectWidth
   *   The width of the rectangle.
   *
   * @param rectHeight
   *   The height of the rectangle.
   */
  public Alphabetical(int rectWidth, int rectHeight) throws Exception {
    // Sanity check
    if (rectWidth <= 0) {
      throw new Exception("Rectangle width must be positive");
    } else if (rectHeight <= 0) {
      throw new Exception("Rectangle height must be positive");
    } // if/else
    // Set up the fields
    this.width = rectWidth;
    this.height = rectHeight;
  } // Rect(String)

  // +--------------------+------------------------------------------
  // | AsciiBlock methods |
  // +--------------------+

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
    String finalRow = "";
    if ((i < 0) || (i >= this.height())) {
      throw new Exception("Invalid row " + i);
    } // if
    for (int j = 0; j < width; j++) {
      int index = ((i * width) + j) % 26;
      char letter = getLetter(index);
      finalRow += letter;
    } // for
    return finalRow;
  } // row(int)

  /**
   * Determine the height of the block.
   *
   * @return the height.
   */
  public int height() {
    return this.height;
  } // height()

  /**
   * Determine the width of the block.
   *
   * @return the width.
   */
  public int width() {
    return this.width;
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
    return (other instanceof Alphabetical)
      && (this.width == (((Alphabetical) other).width))
      && (this.height == ((Alphabetical) other).height);
  } // eqv(AsciiBlock)

  // +---------------+-----------------------------------------------
  // | Other methods |
  // +---------------+

  /**
   * Finds the letter associated with the given index.
   * @param index
   * The integer value of the letter to be returned.
   *
   * @pre index value is between 0 and 25 (inclusive).
   *
   * @return the character value of the index.
   */
   public char getLetter (int index) {
    return ((char)(index + VALUE_OF_BIG_A));
   } // getLetter(int)
} // class Alphabetical
