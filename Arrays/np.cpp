/*

Given an integer array nums and an integer k, return the k most frequent elements.
Input:  nums = [1,1,1,2,2,3], k = 2
Output: [1, 2]

a bit about the heaps.

A heap is a data structure that always keeps the smallest or largest element at the top,
 so you can access it in O(1). When you add or remove elements, it reorganizes itself in O(log n). "



push, pop - logn.
heap is a complete tree - every level is fully filled, no gaps or missing childran
its stored as an array. if parent is at i, its children will be at 2i+1, 2i+2.

Arrays sit in contiguous memory. When the CPU loads index i, it also loads i+1, i+2 into cache automatically.
Tree nodes are scattered across memory — every pointer follow is a potential cache miss.

height of the tree = O(logn)

n (number of nodes in a tree of height h ) =  1 + 2 + 4 + 2^h = 2^h - 1 = 2^h.
h(height) = Log(n)

*/
#include <bits/stdc++.h>
#include <iostream>
using namespace std;

// ---- APPROACH 1: Brute Force O(n x k) ----
vector<int> bruteForce(vector<int>& nums, int k) {
    unordered_map<int, int> freq;
    for (int n : nums) freq[n]++;

    vector<pair<int,int>> sellers(freq.begin(), freq.end());
    vector<int> result;
    vector<bool> used(sellers.size(), false);

    for (int i = 0; i < k; i++) {
        int maxCount = -1, maxIdx = -1;
        for (int j = 0; j < sellers.size(); j++) {
            if (!used[j] && sellers[j].second > maxCount) {
                maxCount = sellers[j].second;
                maxIdx = j;
            }
        }
        used[maxIdx] = true;
        result.push_back(sellers[maxIdx].first);
    }
    return result;
}

// ---- APPROACH 2: Sort O(n log n) ----
vector<int> sortBased(vector<int>& nums, int k) {
    unordered_map<int, int> freq;
    for (int n : nums) freq[n]++;

    vector<pair<int,int>> sellers(freq.begin(), freq.end());
    sort(sellers.begin(), sellers.end(), [](auto& a, auto& b) {
        return a.second > b.second;
    });

    vector<int> result;
    for (int i = 0; i < k; i++)
        result.push_back(sellers[i].first);

    return result;
}

// ---- APPROACH 3: Min Heap O(n log k) ----
vector<int> minHeap(vector<int>& nums, int k) {
    unordered_map<int, int> freq;
    for (int n : nums) freq[n]++;

    priority_queue<
        pair<int,int>,
        vector<pair<int,int>>,
        greater<pair<int,int>>
    > pq;

    for (auto& [seller, count] : freq) {
        pq.push({count, seller});
        if (pq.size() > k)
            pq.pop();
    }

    vector<int> result;
    while (!pq.empty()) {
        result.push_back(pq.top().second);
        pq.pop();
    }
    return result;
}

// ---- APPROACH 4: Bucket Sort O(n) ----
vector<int> bucketSort(vector<int>& nums, int k) {
    unordered_map<int, int> freq;
    for (int n : nums) freq[n]++;

    vector<vector<int>> bucket(nums.size() + 1);
    for (auto& [seller, count] : freq)
        bucket[count].push_back(seller);

    vector<int> result;
    for (int i = bucket.size() - 1; i >= 0 && result.size() < k; i--)
        for (int seller : bucket[i])
            result.push_back(seller);

    return result;
}

// ---- HELPER: Print results ----
void printResult(string label, vector<int>& result) {
    cout << label << ": [";
    for (int i = 0; i < result.size(); i++) {
        cout << result[i];
        if (i < result.size() - 1) cout << ", ";
    }
    cout << "]\n";
}

// ---- MAIN ----
int main() {
    // Simulating sellerIDs appearing in orders
    // seller 3 → 5 orders (most frequent)
    // seller 1 → 3 orders
    // seller 2 → 2 orders
    // seller 4 → 1 order
    vector<int> orders = {3, 3, 3, 3, 3, 1, 1, 1, 2, 2, 4};
    int k = 2; // top 2 sellers

    cout << "Orders: [3,3,3,3,3,1,1,1,2,2,4]\n";
    cout << "K = " << k << " (top " << k << " sellers)\n\n";

    auto r1 = bruteForce(orders, k);
    auto r2 = sortBased(orders, k);
    auto r3 = minHeap(orders, k);
    auto r4 = bucketSort(orders, k);

    printResult("Brute Force  O(n x k)    ", r1);
    printResult("Sort         O(n log n)  ", r2);
    printResult("Min Heap     O(n log k)  ", r3);
    printResult("Bucket Sort  O(n)        ", r4);

    cout << "\nExpected top " << k << " sellers: 3 (5 orders), 1 (3 orders)\n";

    return 0;
}