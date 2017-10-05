package professional.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test0 {
	public static void main(String args[]) throws InterruptedException {
		Runnable r1 = new Run();
		Thread t1 = new Thread(r1);
		t1.start();// 运行线程t1
		Runnable r2 = new Run1();
		Thread t2 = new Thread(r2);
		t2.start();// 运行线程t2
	}
}

// 这里采用单例模式实现A，就是说整个过程只会产生一个A的实例。详见《java与设计模式》
class Singleton {
	private static final Singleton singleton = new Singleton();
	private int index = -1;
	private Lock alock;
	private Condition condition;

	private Singleton() {
		alock = new ReentrantLock();
		condition = alock.newCondition();
	}

	public void f() throws InterruptedException {
		alock.lock();
		try {
			while (index <= 0) // 当条件i<=0时，当前线程将被挂起，必须等待T.signalAll()来唤醒
				condition.await();
			System.out.println("I'm released!");
		} finally {
			alock.unlock();
		}
	}

	public void f1() {
		alock.lock();// 这对lock和unlock必须有，否则程序出错，具体原因还在想。。。
		try {
			index = 1;// 如果这里i=0，那么线程t1将会继续被阻塞，程序陷入死锁
			System.out.println("I'll help you!");
			// T.signalAll()该在哪里调用呢，原则上当对象的状态向着有利于等待线程的方向变化时调用
			condition.signalAll();// 唤醒因为该条件无法满足而被阻塞的进程，
			// 这里注意：只是唤醒，但是并不一定就能执行，还需要CPU的调度，并且需要再次判定i与0的大小
		} finally {
			alock.unlock();
		}
	}

	public static Singleton getInstance() {
		return singleton;
	}
}

class Run implements Runnable {
	public void run() {
		Singleton a = Singleton.getInstance();
		try {
			a.f();
		} catch (InterruptedException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}
}

class Run1 implements Runnable {
	public void run() {
		Singleton b = Singleton.getInstance();
		b.f1();
	}
}
