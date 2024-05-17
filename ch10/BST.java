private class BST<Key> {
    private Key key;
    private BST left;
    private BST right;

    public BST(Key key, BST left, BST Right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    public BST(Key key) {
        this.key = key;
    }

    static BST find(BST T, Key sk){
        if(T == null) 
            return null;
        if(T.key == sk)
            return T;
        else if(T.key < sk)
            return find(T.left, sk);
        else
            return find(T.right, sk);
    }

    static BST insert(BST T, Key ik){
        /*better than create directly*/
        if(T == null) 
            return new BST(ik);
        if(T.key < ik)
            T.left = insert(T.left, ik);
        else
            T.right = insert(T.right, ik);    
        return T;
        }
}