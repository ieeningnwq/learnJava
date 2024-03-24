package com.ieening.learnConcurrency.threadNotifyWait.AsynchronousResult;

public class Executor<T> {
    public Future<T> execute(final Callable<T> task) {
        final Object lock = new Object();
        final ExecutorThread<T> thread = new ExecutorThread<>(task, lock);
        thread.start();
        return new Future<T>() {

            @Override
            public T get() throws Exception {
                synchronized (lock) {
                    while (!thread.isDone()) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                        }
                    }
                    if (thread.exception != null) {
                        throw thread.exception;
                    }
                    return thread.result;
                }
            }
        };
    }

    static class ExecutorThread<T> extends Thread {
        private T result = null;
        private Exception exception = null;
        private boolean done = false;
        private Callable<T> task;
        private Object lock;

        public ExecutorThread(Callable<T> task, Object lock) {
            this.task = task;
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                result = task.call();
            } catch (Exception e) {
                exception = e;
            } finally {
                synchronized (lock) {
                    done = true;
                    lock.notifyAll();
                }
            }
        }

        public T getResult() {
            return result;
        }

        public boolean isDone() {
            return done;
        }

        public Exception getException() {
            return exception;
        }
    }

    public static void main(String[] args) {
        Executor<Integer> executor = new Executor<>();
        // 子任务
        Callable<Integer> subTask = new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                // 执行异步任务
                int millis = (int) (Math.random() * 1000);
                Thread.sleep(millis);
                return millis;
            }
        };
        // 异步调用，返回一个 Future 对象
        Future<Integer> future = executor.execute(subTask);
        // 执行其他操作
        try {
            Integer result = future.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
