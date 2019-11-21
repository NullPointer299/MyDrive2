package model.dto.tree;

public class TreeManagement {

    private final MainTree mainTree;
    private final SubTree subTree;

    public TreeManagement(final MainTree mainTree, final SubTree subTree) {
        this.mainTree = mainTree;
        this.subTree = subTree;
    }

    public MainTree getMainTree() {
        return mainTree;
    }

    public SubTree getSubTree() {
        return subTree;
    }
}
