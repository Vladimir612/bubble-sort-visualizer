<h1>Bubble sort visualizer</h1>

<p>What we want is to visualize bubble sort algorithm in time using Java FX library for GUI, but first let's see what is bubble sort algorithm</p>

<p>The bubble sort algorithm is an array sorting algorithm. We are always comparing two elements that are next to each other and if necessary, we change their places.
If we want to sort array ascending, the condition for changing places of elements will be that the second one is smaller than the first one. So we will go through whole array and do this to every pair of elements. That will be the end of first iteration and the important thing is that after this we will have one element on its final position in array, and it will be on the end of that array. So we don't want to compare later that element with anything. If we are sorting our array ascending, that element will be the biggest element. After second iteration through array we will have another element on its final position. When we have all elements on their final positions the algorithm is finished.</p>

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
  <li>when we compare two elements (elements are yellow)</li>
  <li>when we exchange places to those elements (elements are blue)</li>
  <li>when element is on its final position (element is green)</li>
</ul>

<h3>Comparing two elements</h3>

![image](https://user-images.githubusercontent.com/53167193/170163013-d72e68f5-1d2d-472a-97e3-6a3b5e8628d6.png)

<h3>Exchanging places</h3>

![image](https://user-images.githubusercontent.com/53167193/170163087-1dd54d6c-68f3-4c03-8f4d-f67d55f749e1.png)

<h3>Element is on its final position</h3>

![image](https://user-images.githubusercontent.com/53167193/170163641-e3d7fca3-fbfb-46c7-9151-ea22c4448f13.png)

<p>History list will be filled each time when array state has changed, if we are comparing elements or if we exchange them</p>

<p>That list will also have first state of array and that's initial state. The last element of the list will be sorted array. So status of an array in history list can be: INITIAL, COMPARED, EXCHANGED or SORTED. Items in list will also have information about how many elements in an array are currently on their final position.</p>

<p>So item in history list for example can be: </p>

![image](https://user-images.githubusercontent.com/53167193/170387128-aafaecce-c41f-4d3c-949e-d56e80de9264.png)

or 

![image](https://user-images.githubusercontent.com/53167193/170387169-c6326691-bace-471d-8d06-e24f1d33ac9d.png)

or

![image](https://user-images.githubusercontent.com/53167193/170387189-7954290b-cf40-46e9-a5aa-5c2c3fc44ccb.png)

or

![image](https://user-images.githubusercontent.com/53167193/170387220-7d9e5884-2a3b-4b74-9efb-959a77d8baa9.png)

<p>Now when we know what are the tasks, we can continue to logic that we want to implement</p>

<p>What we must understand in this logic is that we have multiple things on screen that all dictate to array how to be displayed, therefore, before dividing this program into small parts, we must notice the connection between the functionalities. Things that affect diplayed array are: </p>

<ul>
  <li>timer (After every second by default array will change its state, to control timer we use start/stop button and slider to change sorting speed)</li>
  <li>next iteration button (Every time we click on it the array will also change its state)</li>
  <li>When we click on item inside of history list, we want to display the array as it was at that moment</li>
  <li>History list also change every time at the same moment as array</li>
</ul>

<p>To manage that history list update and the update of displayed array are happening at the same time we will set that one of these functions triggers the other one</p>

<p>How to choose which function should trigger the other one:</p>

<p>If we choose that updateDisplayedArray function trigger addNextHistoryItem function what will happen?</p>

Timer will run. updateDisplayedArray function will be called on each second and that's okay, but when we click on item in history list that will call updateDisplayedArray function and that will call addNextHistoryItem and that will call again updateDisplayedArray. We don't want that.

We want that addNextHistoryItem triggers updateDisplayedArray and then there is no problem.

<p>The biggest problem we have is to go to the previous state of an array when we click on history item. The easiest way to do that is to store every array state in some list(ArrayList), and when we click on history item thanks to index we will know what array state we want to get from list and call updateDisplayedArray function.</p>

Later we will apply this to our code but before that let's make our GUI using JavaFx.

To get started with JavaFx you can watch this short video after which you will know how to initialize the project. I called it BubbleSortApplication

Main layout elements on our screen are:

![image](https://user-images.githubusercontent.com/53167193/170703472-6d2f3a75-04ef-45c6-9273-37c1aafa2255.png)

On scene element we will customize layout and then we will set that scene on our window. Buttons will be in buttonGroup. In tileArray we will display our array. What we have on scene to arrange elements more easily is borderPane. On left side of borderPane we will put historyLayout, and on the center we will put our mainLayout. We need slider for adjusting speed of sorting, and scrollPane is for mainLayout to allow scrolling if we have very large array. HistoryListView will be in historyLayout.

Now lets do all this in our start function.

The initialization, paddings, sizes and default values of elements:

![image](https://user-images.githubusercontent.com/53167193/170712054-6410706b-50fb-402c-b6a9-6bddf42db4f6.png)

Setting the handler on buttons:

![image](https://user-images.githubusercontent.com/53167193/170712523-7a526f60-dd7e-4030-8ab0-f41392e8ce16.png)

setOnAction will trigger ActionEvent and function handle will be called:

![image](https://user-images.githubusercontent.com/53167193/170713405-44515d2b-b255-4e7c-92d2-1f03177ac9c4.png)

Later we will fill these conditions with more code.

Putting elements inside of parent elements:

![image](https://user-images.githubusercontent.com/53167193/170714711-08ed4872-8069-42f3-b381-1524f4298bb0.png)

We need to have content for our historyListView and we will store that content in history element. Lets first define it outside of start function scope:

![image](https://user-images.githubusercontent.com/53167193/170715047-38d34883-6fd3-4e1a-8d31-84b527b615a2.png)

And now in start function lets initialize that content and arrange elements in historyLayout:

![image](https://user-images.githubusercontent.com/53167193/170715538-e15abd4a-fbd4-408e-90e9-ebb4f8986add.png)

Adding scene to the window and displaying the window withh show function.

![image](https://user-images.githubusercontent.com/53167193/170715774-b79a8f08-a2b3-4ec3-9961-219aaa8283c7.png)

The question is which array we want to sort, we can provide that array from Starter class or if we call BubbleSortApplication directly we will sort random generated array of 99 elements.

Starter class:

![image](https://user-images.githubusercontent.com/53167193/170725787-06354dcb-62d4-43d0-9f41-b43a215655ad.png)

To send array to BubbleSortApplication we needed to pass it as an array od strings. There is no other way. Later we will again transform it to int array so we can use it. 

Let's see what kind of array we want, this array should help us in updateDisplayedArray and addNextHistoryItem functions. We don't want to send basic array with only numbers to those functions. We want to make a special class that has all the necessary data about array and elements in that array. The data we need:

<ul>
  <li><b>number of sorted elements</b> for addNextHistoryItem function</li>
  <li><b>Status of array(INITIAL, COMPARED, EXCHANGED, SORTED)</b> for addNextHistoryItem function</li>
  <li><b>The color of element in array </b> for updateDisplayedArray function. We will know color based on the ElementStatus(DEFAULT - gray, COMPARED - yellow, EXCANGED - blue, FINAL - green). This has nothing to do with the status of the array itself.</li>
  <li><b>The elements(int values)</b> for updateDisplayedArray function</li>
</ul>

ElementStatus enum:

![image](https://user-images.githubusercontent.com/53167193/170729923-e8600e62-70bb-4b24-b2bf-b3de12454447.png)

ArrayStatus enum:

![image](https://user-images.githubusercontent.com/53167193/170729998-16ff3237-4bcd-4792-a851-5812ec627ae6.png)

So array of values should contain the int value and enum ElementStatus. This will be the special class ArrayElement:

![image](https://user-images.githubusercontent.com/53167193/170730523-b0843838-2cf8-4fed-a392-96952a6aac7c.png)

There are getters and setters in this class but I guess you know how they work.

Now let's use this in our final ArrayState class where we will apply data like ArrayStatus and number of sorted elements.

![image](https://user-images.githubusercontent.com/53167193/170731322-a69d8fcb-7be5-4a1e-a710-eec835640b9b.png)

Now let's continue to our program and use this class where we need to. We passed our array to BubbleSortApplication, we have to transform that to our ArrayState class and use it as arrayState object. Or if we din't pass it we have to generate random array.

![image](https://user-images.githubusercontent.com/53167193/170744900-ba8fe823-e163-408a-8952-e12fd7f3fe39.png)

![image](https://user-images.githubusercontent.com/53167193/170735811-d8708d3e-4299-442d-a704-957739605c75.png)

As I told earlier we needed to transform this array to array of ints to use it in our generateArr function. 

generateArr() and generateRandomArr() in HelperFunctions.java: 

![image](https://user-images.githubusercontent.com/53167193/170732743-101f38fe-0fd1-4a68-8af8-af9920481825.png)

![image](https://user-images.githubusercontent.com/53167193/170734660-5985703f-e712-4f06-b7a4-d7ad87e207c2.png)

In generateArr we transform our int array to ArrayState(Every element has DEFAULT value for ElementStatus, array has INITIAL ArrayStatus, and 0 sorted elements) and in generateRandomArr we generate random int array which we will later transform to ArrayState.

When we have an initial array that suits us, we can display it on the screen:

![image](https://user-images.githubusercontent.com/53167193/170738930-8086b1e3-ec3c-4771-aee6-d6fc1d56f341.png)

updateDisplayedArray() in HelperFunctions.java:

![image](https://user-images.githubusercontent.com/53167193/170741981-b4f84f8c-38f4-403b-a3e1-9264ef9fbbb6.png)

This function will be called multiple times so first we want to delete every element so we can draw them again and then if passed array has element inside we will go through each and draw them. We need to set number inside, padding, shadow, text color and background color which depends on ElementStatus value. Than we are adding that element to our tileArray.

We said earlier that we want to make a list(statesOfArray) that contains every array state in proccess of sorting so we can go back to previous states.

![image](https://user-images.githubusercontent.com/53167193/170743867-ac842a0d-cac0-44e7-bc1e-94bc9e0abe2b.png)

Lets do that with special kind of bubbleSort function. In BubbleSortApplication after we initialize arrayState we call it:

![image](https://user-images.githubusercontent.com/53167193/170743646-97d4bda0-7fe2-49dc-b6e1-7908e9a133a9.png)

We passed statesOfArray which will be full when the function ends, and initial array that should be sorted at the end.

![image](https://user-images.githubusercontent.com/53167193/170764859-a1cc77d5-eb05-4a0d-a6ba-9b43894400dd.png)

IMPORTANT. The reason why we are making a copy of passedArray is that we are sending reference to the function and if we use passedArray instead of initArray in setting the initArrayState than the first state will be sorted array because passedArray is sorted at the end of the function.

So we made a copy of passedArray and put it as an initial state and first element of statesOfArray list.

![image](https://user-images.githubusercontent.com/53167193/170767815-8898e9b8-b1a0-4157-90e0-a17039243f7d.png)

![image](https://user-images.githubusercontent.com/53167193/170766503-906daa22-5069-4cd3-a8b6-f86af6b28681.png)

Now we notice similarities with the universal bubble sort function. On each iteration(comparison between pairs) we want to insert whole array state in our list. So in
these two for loops we want another for loop that will go through whole array. If we are currently on elements that doesn't compare we want them to be gray(DEFAULT
STATUS), if elements are comparing(always two elements) we want them to be yellow(COMPARED STATUS) and if elements are already on their final position we want them to
be green(FINAL STATUS). Important thing here to know that our element is on its final position is that we are on elements that shouldn't be compared at all at that 
current iteration. The condition is similar as the condition for j index to break from for loop. J went to n - i - 1 and then we stop comparing. Now we want every 
other element from that point to be marked as green.

When we finished our pass through array we put it as new element of statesOfArray list but now it has COMPARED status and the number of sorted elements is same as 
index i.

We added compared array state to list but also we should add exchanged array if elements exchanged places. That's why we use condition: 

![image](https://user-images.githubusercontent.com/53167193/170771221-d7206c08-5338-4d8f-a23b-7499bf5aa7aa.png)

We exchange their places and then do the same thing as earlier for compared array only this time with EXCHANGED status.

All that remained is to insert array with FINAL status with sorted elements. It is similar to code where we put our initial array to statesOfArray:

![image](https://user-images.githubusercontent.com/53167193/170772492-7be53cc8-981c-4261-b9c4-a17e584cdc39.png)

Now when we have everything that we need let's define our timeline(in background that is thread) on which things are gonna happen:

![image](https://user-images.githubusercontent.com/53167193/170772762-dbf30e49-1e9f-4ba1-8104-cb1c6720e84f.png)

![image](https://user-images.githubusercontent.com/53167193/170772863-07c18c74-b217-41eb-a829-4f417bad4620.png)

By default on each second addNextHistoryItem function will be called which will trigger updateDisplayedArray.

![image](https://user-images.githubusercontent.com/53167193/170773040-5f8e4022-ec0f-48c6-8235-9f336f0d3910.png)

This function has to be synchronized because two things are calling her: timeline and next iteration button. If happens that we click the button at the same time when
timeline calls addNextHistoryItem java will know how to process one thing first and then another thanks to that.

We take current number of items in history list as count and if it is bigger or equal as stateOfArray length we don't want to add next history item. Then with that 
count we take item from statesOfArrayList. Take status from that element and number of sorted elements and make corresponding text. Then at the end of current history list we add that item with text and also call updateDisplayedArray(). That is the trigger I talked about.

Lets fill are handle function with code:

![image](https://user-images.githubusercontent.com/53167193/170779113-6026653e-c1d4-4e6f-b57f-26c170899e97.png)

When we click play we run our timeline and number of times it should call addNextHistoryItem function is equal to size of the array. Stop will pause timeline. Next 
iteration button will call addNextHistoryItem function independently of the timeline.

Slider should change the speed of the timeline:

![image](https://user-images.githubusercontent.com/53167193/170779926-79bfc777-0295-4bcd-9eb1-7c101bdc68c7.png)

Things that we should focus here are that we added change listener on slider, the rate we are setting and when we want to continue timeline. On slider we used miliseconds as values, the rate of timeline is in 1 / seconds format, that's why we are dividing 1000 with slider value. If timeline doesn't run at the moment we don't want to play it. If it's currently running than we stop it for a moment and then start it so it can use new rate.

The only thing left is to set that on click on item in history list we display array from that moment on screen.

![image](https://user-images.githubusercontent.com/53167193/170781039-ff6da546-d3a0-4442-b98e-1feb7329a917.png)

We take the index of an element from list because it's the same as the index of an array from statesOfArray list, and use that index to find wanted array and call
function updateDisplayedArray.

That's it, we finished our task.

![image](https://user-images.githubusercontent.com/53167193/170781892-b149fc65-e821-4b09-a603-8b94ed6fbf21.png)

