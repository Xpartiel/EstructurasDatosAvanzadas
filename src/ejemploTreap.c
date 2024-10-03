// A sample C++ program to implement the Treap data structure  
  
#include <iostream>  
#include <cstdlib>  
#include <ctime>  
using namespace std;  
  
// A struct named TreapNod is declared that will act as a single node of the Treap data structure  
struct TreapNod  { //node declaration  
     
   int data;  
   int priority;  
   TreapNod* l, *r;  
  
   TreapNod(int d) { //constructor  
      this->data = d;  
      this->priority = rand() % 100;  
      this->l= this->r = nullptr;  
   }  
};  
  
  
// A function named rotLeft is written to perform the left rotation operation on the Treap to whom the root of the Treap is passed as a parameter   
void rotLeft(TreapNod* &root) { //left rotation  
   TreapNod* R = root->r;  
   TreapNod* X = root->r->l;  
   R->l = root;  
   root->r= X;  
   root = R;  
}  
  
// A function named rotRight is written to perform the right rotation operation on the Treap to whom the root of the Treap is passed as a parameter  
void rotRight(TreapNod* &root) { //right rotation  
   TreapNod* L = root->l;  
   TreapNod* Y = root->l->r;  
   L->r = root;  
   root->l= Y;  
   root = L;  
}  
  
  
// A function named insertNod is written that will be used to add a new node to the already existing Treap to whom the root of the Treap is passed as a parameter   
void insertNod(TreapNod* &root, int d) { //insertion  
   if (root == nullptr) {  
      root = new TreapNod(d);  
      return;  
   }  
   if (d < root->data) {  
      insertNod(root->l, d);  
      if (root->l != nullptr && root->l->priority > root->priority)  
      rotRight(root);  
   } else {  
      insertNod(root->r, d);  
      if (root->r!= nullptr && root->r->priority > root->priority)  
      rotLeft(root);  
   }  
}  
  
// A function named searchNod is written that will be used to search the node in the Treap data struture to whom the root of the Treap is passed as a parameter   
bool searchNod(TreapNod* root, int key) {  
   if (root == nullptr)  
      return false;  
   if (root->data == key)  
      return true;  
   if (key < root->data)  
      return searchNod(root->l, key);  
      return searchNod(root->r, key);  
}  
  
  
// A function named deleteNod is written that will be used to delete a node from the Treap data struture to whom the root of the Treap is passed as a parameter   
void deleteNod(TreapNod* &root, int key) {  
   //node to be deleted which is a leaf node  
   if (root == nullptr)  
      return;  
   if (key < root->data)  
      deleteNod(root->l, key);  
   else if (key > root->data)  
      deleteNod(root->r, key);  
      //node to be deleted which has two children  
   else {  
      if (root->l ==nullptr && root->r == nullptr) {  
         delete root;  
         root = nullptr;  
      }  
      else if (root->l && root->r) {  
         if (root->l->priority < root->r->priority) {  
            rotLeft(root);  
            deleteNod(root->l, key);  
         } else {  
            rotRight(root);  
            deleteNod(root->r, key);  
         }  
      }  
      //node to be deleted has only one child  
      else {  
         TreapNod* child = (root->l)? root->l: root->r;  
         TreapNod* curr = root;  
         root = child;  
         delete curr;  
      }  
   }  
}  
  
// A function named displayTreap is written that will be used to print all the values of the Treap data struture to whom the root of the Treap is passed as a parameter   
void displayTreap(TreapNod *root, int space = 0, int height =10) { //display treap  
   if (root == nullptr)  
      return;  
   space += height;  
   displayTreap(root->l, space);  
   cout << endl;  
   for (int i = height; i < space; i++)  
      cout << ' ';  
      cout << root->data << "(" << root->priority << ")\n";  
      cout << endl;  
   displayTreap(root->r, space);  
}