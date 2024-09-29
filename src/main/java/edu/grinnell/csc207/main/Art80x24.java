package edu.grinnell.csc207.main;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.Boxed;
import edu.grinnell.csc207.blocks.Grid;
import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.HComp;
import edu.grinnell.csc207.blocks.Rect;
import edu.grinnell.csc207.blocks.VAlignment;
import edu.grinnell.csc207.blocks.VComp;
import java.io.PrintWriter;

/**
 * Create and print an amazing 80x24 ASCII artwork.
 *
 * @author Your Name Here
 * @author Your Name Here
 */
public class Art80x24 {
  /**
   * Create the artwork.
   *
   * @param args Command-line arguments (currently ignored).
   * @exception Exception If something goes wrong with one of the underlying classes.
   */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);

    AsciiBlock star = new Boxed(new Rect('*', 1, 1));
    AsciiBlock flagCorner = new Grid(star, 5, 3);
    AsciiBlock redStarLine = new Grid(new Rect('R', 1, 1), 65, 3);
    AsciiBlock whiteStarLine = new Grid(new Rect('W', 1, 1), 65, 3);
    AsciiBlock fullRedStarLine = new Grid(new Rect('R', 1, 1), 80, 3);
    AsciiBlock fullWhiteStarLine = new Grid(new Rect('W', 1, 1), 80, 3);
    AsciiBlock starStripes =
        new VComp(HAlignment.CENTER, new AsciiBlock[] {redStarLine, whiteStarLine, redStarLine});
    AsciiBlock starLine = new HComp(VAlignment.TOP, new AsciiBlock[] {flagCorner, starStripes});
    AsciiBlock lineBlock =
        new VComp(
            HAlignment.CENTER,
            new AsciiBlock[] {
              fullWhiteStarLine,
              fullRedStarLine,
              fullWhiteStarLine,
              fullRedStarLine,
              fullWhiteStarLine
            });
    AsciiBlock americanFlag = new VComp(HAlignment.CENTER, new AsciiBlock[] {starLine, lineBlock});
    AsciiBlock.print(pen, americanFlag);
    pen.close();
  } // main(String[])
} // class Art80x24
