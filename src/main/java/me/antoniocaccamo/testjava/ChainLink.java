package me.antoniocaccamo.testjava;

import java.util.logging.Logger;

/**
 * @author antoniocaccamo  on 19/10/2020
 */
enum Side {NONE, LEFT, RIGHT}

public class ChainLink {

    private static final Logger logger = Logger.getLogger(ChainLink.class.getName());

    private ChainLink left, right;

    public void append(ChainLink rightPart) {
        if (this.right != null) {
            throw new IllegalStateException("Link is already connected.");
        }
        this.right = rightPart;
        rightPart.left = this;
    }

    public Side longerSide() {

        int leftSideCount  = left  == null ? 0 : chainLinkSideCounter(this, this, Side.LEFT);
        int rightSideCount = right == null ? 0 : chainLinkSideCounter(this, this, Side.RIGHT);

        logger.info(String.format("%d %d", leftSideCount, rightSideCount));

        if ( leftSideCount < 0 || rightSideCount < 0 )
            return Side.NONE;

        if ( leftSideCount < rightSideCount )
            return Side.RIGHT;

        if ( rightSideCount < leftSideCount )
            return Side.LEFT;


        return Side.NONE;
    }


    private int chainLinkSideCounter(ChainLink link, ChainLink root, Side side) {

        ChainLink next = null;

        if (Side.LEFT.equals(side)) {
            if (link.left == null || this == root)
                return 1;
            next = link.left;
        }

        if (Side.RIGHT.equals(side)) {
            if (link.right == null)
                return 0;

            if (link.right == root )
                return -1;
            next = link.right;
        }

        int count = chainLinkSideCounter(next, root, side);

        if (count < 0)
            return -1;

        return count + 1;

    }

    public static void main(String[] args) {
        ChainLink   left = new ChainLink();
        ChainLink middle = new ChainLink();
        ChainLink  right = new ChainLink();
        left.append(middle);
        middle.append(right);
        //
        //right.append(left);
        System.out.println(right.longerSide());
    }
}
