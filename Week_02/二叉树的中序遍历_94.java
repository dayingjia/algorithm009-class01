递归法
class Solution {
    List<Integer> resList = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        getInorderTraversal(root);
        return resList;
    }

    private void getInorderTraversal(TreeNode node){
        if(node == null){
            return;
        }
        getInorderTraversal(node.left);
        resList.add(node.val);
        getInorderTraversal(node.right);
    }
}
遍历法
class Solution {
    List<Integer> resList = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()){
            while(cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            resList.add(cur.val);
            cur = cur.right;
        }
        return resList;
    }
}