package main;

import java.util.ArrayList;

public class IntTree {
	public IntTreeNode overallRoot;
	
	public IntTree(String treeString) {
		buildTree(treeString);
	}
	
	private void buildTree(String treeString) {
		String[] arr = treeString.split(" ");
		if (arr.length >= 1 && !arr[0].equals("")) {
			overallRoot = new IntTreeNode(Integer.parseInt(arr[0]));	
			for (int i = 1; i < arr.length; i++) {
				IntTreeNode curNode = overallRoot;
				int num = Integer.parseInt(arr[i]);
				boolean foundPlace = false;
				while (!foundPlace) {
					if (num < curNode.data) {
						if (curNode.left != null) {
							curNode = curNode.left;
						} else {
							curNode.left = new IntTreeNode(num);
							foundPlace = true;
						} 
					} else if (num > curNode.data) {
						if (curNode.right != null) {
							curNode = curNode.right;
						} else {
							curNode.right = new IntTreeNode(num);
							foundPlace = true;
						}
					}
				}
			}
		}
	}
	
	public void addValue(int value) {
		IntTreeNode curNode = overallRoot;
		boolean foundPlace = false;
		while (!foundPlace) {
			if (value < curNode.data) {
				if (curNode.left != null) {
					curNode = curNode.left;
				} else {
					curNode.left = new IntTreeNode(value);
					foundPlace = true;
				} 
			} else if (value > curNode.data) {
				if (curNode.right != null) {
					curNode = curNode.right;
				} else {
					curNode.right = new IntTreeNode(value);
					foundPlace = true;
				}
			}
		}
	}
	
	
	public String toPreOrderString() {
		if (overallRoot != null) {
			return preOrderR(overallRoot);
		}
		return "tree is empty";
	}
	
	private String preOrderR(IntTreeNode n) {
		String str = "[" + n.data + "]";
		str += "(";
		if (n.left != null) {
			str += preOrderR(n.left);
		}
		str += ")";
		str += "(";
		if (n.right != null) {
			str += preOrderR(n.right);
		}
		str += ")";
		return str;
	}
	
	public String toInOrderString() {
		if (overallRoot != null) {
			return inOrderR(overallRoot);
		}
		return "tree is empty";
	}
	
	private String inOrderR(IntTreeNode n) {
		String str = "";
		str += "(";
		if (n.left != null) {
			str += inOrderR(n.left);
		}
		str += ")";
		str += "[" + n.data + "]";
		str += "(";
		if (n.right != null) {
			str += inOrderR(n.right);
		}
		str += ")";
		return str;
	}
	
	public String toPostOrderString() {
		if (overallRoot != null) {
			return postOrderR(overallRoot);
		}
		return "tree is empty";
	}
	
	private String postOrderR(IntTreeNode n) {
		String str = "";
		str += "(";
		if (n.left != null) {
			str += postOrderR(n.left);
		}
		str += ")";
		str += "(";
		if (n.right != null) {
			str += postOrderR(n.right);
		}
		str += ")";
		str += "[" + n.data + "]";
		return str;
	}
	
	
	public String toPrettyPrint() {
		if (overallRoot != null) {
			return arrayToStr(prettyTreeR(overallRoot));
		}
		return "tree is empty";
	}
	
	public ArrayList<String> prettyTreeR(IntTreeNode n) {
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> leftList = null;
		ArrayList<String> rightList = null;
		String dataStr = "[" + n.data + "]";
		String slantString = "";
		String dataString = "";
		
		String leftSpacing = "";
		String rightSpacing = "";
		int from = 0;
		if (n.left != null) {
			leftList = prettyTreeR(n.left);
			leftSpacing = addStrs("", leftList.get(0).length(), " ");
			dataString += leftSpacing;
		}
		dataString += dataStr;
		if (n.right != null) {
			rightList = prettyTreeR(n.right);
			rightSpacing = addStrs("", rightList.get(0).length(), " ");
			dataString += rightSpacing;


		}
		//reserve self
		list.add(dataString);
		
		
		
		
		//for combining leftList and rightList with current list.
		//combines left list and right list so that each same index of both lists combines as a new index of the current list.
		if (leftList != null || rightList != null) {
			//reserve for slants if child nodes
			list.add(slantString);
			
			boolean leftBigger;
			int bigSize = 0;
			int leftSize = 0;
			int rightSize = 0;
			if (leftList != null) {
				leftSize = leftList.size();
			}
			if (rightList != null) {
				rightSize = rightList.size();
			}
			
			if (leftSize > rightSize) {
				leftBigger = true;
				bigSize = leftSize;
			} else if (rightSize > leftSize) {
				leftBigger = false;
				bigSize = rightSize;
			} else {
				leftBigger = false;
				bigSize = rightSize;
			}
			//System.out.println("Data in current node: " + n.data);
			//System.out.println("bigSize: " + bigSize + " " + "leftBigger: " + leftBigger);
			String spacing = addStrs("", dataStr.length(), " ");
			//here is probably where spacing gets added
			for (int i = 0; i < bigSize; i++) {
				if (leftBigger) {
					if (i < rightSize) {
						list.add(leftList.get(i) + spacing + rightList.get(i));
					} else {
						list.add(leftList.get(i) + spacing);
					}
				}
				else {
					if (i < leftSize) {
						list.add(leftList.get(i) + spacing + rightList.get(i));
					} else {
						list.add(leftSpacing + spacing + rightList.get(i));
					}
				}
				
			}
			/*
			if (leftList != null) {
				//System.out.println(leftList.get(0));
				slantString += addStrs("", findDistToChar(0, "]", leftList.get(0))," ") + "/";
				from = findDistToChar(0, "]", list.get(2));
			}
			slantString += spacing;
			if (rightList != null) {
				slantString +=  addStrs("", findDistToChar(0, "[", rightList.get(0)),"m") + "\\";
				slantString += addStrs("", rightList.get(0).substring( findDistToChar(0, "]", rightList.get(0))).length() + 1," ");
			}
			list.set(1, slantString);
			/*
			if (leftList != null) {
				dataString = addStrs("", findDistToChar(0, "/", list.get(1)) + 1, " ");
				dataString += addStrs("", findDistToChar(0, "[", list.get(0)) - findDistToChar(0, "/", list.get(1)) - 1, "_");
			} else {
				dataString = leftSpacing;
			}
			
			
			dataString += dataStr;
			
			if (rightList != null) {
				dataString += addStrs("", findDistToChar(0, "\\", list.get(1)) - findDistToChar(0, "]", list.get(0)) - 1, "_");
				dataString += addStrs("", rightList.get(0).length(), " ");
			} else {
				dataString += rightSpacing;
			}
			list.set(0, dataString);
			*/
		}	
		
		return list;
	}
	
	private String addStrs(String initStr, int amount, String addedStr) {
		for (int i = 0; i < amount; i++) {
			initStr += addedStr;
		}
		return initStr;
	}
	
	private String arrayToStr(ArrayList<String> strs) {
		String str = "";
		for (int i = 0; i < strs.size(); i++) {
			str += strs.get(i) + "\n";
		}
		return str;
	}
	
	private int findDistToChar(int from, String c, String str) {
		return str.indexOf(c, from);
	}
}

