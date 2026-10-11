class MyCircularDeque {
    int[] deque;
    int front, rear, size, capacity;

    public MyCircularDeque(int k) {
        deque = new int[k];
        capacity = k;
        front = 0;
        rear = -1;
        size = 0;
    }

    public boolean insertFront(int value) {
        if (isFull()) return false;

        front = (front - 1 + capacity) % capacity;
        if (size == 0) front = 0;
        deque[front] = value;

        if (size == 0) rear = front;
        size++;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()) return false;

        rear = (rear + 1) % capacity;
        deque[rear] = value;

        if (size == 0) front = rear;
        size++;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) return false;

        front = (front + 1) % capacity;
        size--;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) return false;

        rear = (rear - 1 + capacity) % capacity;
        size--;
        return true;
    }

    public int getFront() {
        return isEmpty() ? -1 : deque[front];
    }

    public int getRear() {
        return isEmpty() ? -1 : deque[rear];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
}