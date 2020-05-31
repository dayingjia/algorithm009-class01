迭代法
class Solution {
    List<Integer> list = new ArrayList<>();
    public List<Integer> preorder(Node root) {
        getPreorder(root);
        return list;
    }

    public void getPreorder(Node root){
        if(root != null){
            list.add(root.val);
            if(root.children != null){
                for(Node child : root.children){
                    preorder(child);
                }
            }
        }
    }
}
遍历法
class Solution {
    List<Integer> list = new ArrayList<>();
    public List<Integer> preorder(Node root) {
        if(root == null) return list;
        Stack<Node> nodeStack = new Stack<>();
        nodeStack.add(root);
        while(!nodeStack.isEmpty()){
            Node n = nodeStack.pop();
            list.add(n.val);
            if(n.children != null){
                List<Node> child = n.children;
                for(int i = child.size() -1; i >= 0; i--){
                    nodeStack.add(child.get(i));
                }
            }
        }
        return list;
    }
}