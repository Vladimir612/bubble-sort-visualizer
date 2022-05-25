<h1>Bubble sort visualizer</h1>

<p>What we want is to visualize bubble sort algorithm in time using Java FX library for GUI, but first let's see what is bubble sort algorithm</p>

<p>The bubble sort algorithm is an array sorting algorithm. We are always comparing two elements that are next to each other and if necessary, we change their places.
If we want to sort array ascending the condition for changing places of elements will be that the second one is smaller than the first one. So we will go through whole array and do this to every pair of elements. That will be the end of first iteration and the important thing is that after this we will have one element on its final position in array, and it will be on the end of that array. So we don't want to compare later that element with anything. If we are sorting our array ascending that element will be the biggest element. After second iteration through array we will have another element on its final position. When we have all elements on their final positions the algorithm is finished.</p>

The simple code for bubble sort is on next image:

![image](https://user-images.githubusercontent.com/53167193/170382190-ff180a15-8a83-48fc-80d1-dd3b84cbbfa5.png)

<p>Now back to our task</p>

<p>All functionalities that we will have are: </p>

<ul>
  <li><b>play button</b> that will start timer</li>
  <li><b>stop button</b> that will stop timer</li>
  <li><b>next iteration button</b> that will trigger algorithm to move on next iteration of sorting</li>
  <li><b>slider</b> which allows the user to speed up or slow down the sorting</li>
  <li><b>history list</b> which allows the user to go back to any previous state of an array</li>
</ul>

![image](https://user-images.githubusercontent.com/53167193/170161196-399c07cb-3d7b-408f-a661-a377ce0aafec.png)

<h2>Displayed array - what we want to visualize</h2>

<p>In array that will change in time we want to display:</p>

<ul>
  <li>when we compare two elements</li>
  <li>when we exchange places to those elements</li>
  <li>when element is on its final position</li>
</ul>

<h3>Comparing two elements</h3>

![image](https://user-images.githubusercontent.com/53167193/170163013-d72e68f5-1d2d-472a-97e3-6a3b5e8628d6.png)

<h3>Exchanging places</h3>

![image](https://user-images.githubusercontent.com/53167193/170163087-1dd54d6c-68f3-4c03-8f4d-f67d55f749e1.png)

<h3>Element is on its final position</h3>

![image](https://user-images.githubusercontent.com/53167193/170163641-e3d7fca3-fbfb-46c7-9151-ea22c4448f13.png)

<p>History list will be filled each time when array state has changed, if we are comparing elements or if we exchange them</p>

<p>That list will also has first state of array and that's initial state. The last element of list will be sorted array. So status of array in history list can be: INITIAL, COMPARED, EXCHANGED or SORTED. Items in list will also have information how many elements in array are currently on their final positions. </p>

<p>So item in history list for example can be: </p>

![image](https://user-images.githubusercontent.com/53167193/170387128-aafaecce-c41f-4d3c-949e-d56e80de9264.png)

or 

![image](https://user-images.githubusercontent.com/53167193/170387169-c6326691-bace-471d-8d06-e24f1d33ac9d.png)

or

![image](https://user-images.githubusercontent.com/53167193/170387189-7954290b-cf40-46e9-a5aa-5c2c3fc44ccb.png)

or

![image](https://user-images.githubusercontent.com/53167193/170387220-7d9e5884-2a3b-4b74-9efb-959a77d8baa9.png)



















