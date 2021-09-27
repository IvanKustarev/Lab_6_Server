import Commands.Settings.ExecuteThread;
import Commands.Settings.Executor;
import Messenger.Messenger;
import Messenger.Request;
import Messenger.Response;
import WorkWithConsole.ConsoleWorker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExecuteManager {
    private Messenger messenger;
    private Executor executor;
    private ConsoleWorker consoleWorker;

    public ExecuteManager(Messenger messenger, Executor executor, ConsoleWorker consoleWorker) {
        this.messenger = messenger;
        this.executor = executor;
        this.consoleWorker = consoleWorker;
    }

    public void startExecuteUsersCommands() {
        UserCommandExecutor userCommandExecutor = new UserCommandExecutor();
        userCommandExecutor.start();
    }

    private class UserCommandExecutor extends Thread {
        @Override
        public void run() {
            startExecuteUsersCommands();
        }

        public void startExecuteUsersCommands() {
            ForkJoinPool forkJoinPool = new ForkJoinPool(3);
            while (true) {
                List<Request> requests = forkJoinPool.invoke(messenger.new RequestChecker());

                List<ExecuteThread> executeThreads = new ArrayList<>();

                List<Response> responses = new ArrayList<>();

                for (Request request : requests) {
                    if (request.isCommandRequest()) {
                        ExecuteThread executeThread = new ExecuteThread(request, executor);
                        executeThread.executeCommandInThread();
                        executeThreads.add(executeThread);
                    }
                }

                for (ExecuteThread executeThread : executeThreads) {
                    while (true) {
                        try {
                            if (executeThread.getResponse() != null) {
                                break;
                            } else {
                                synchronized (executeThread) {
                                    executeThread.wait(20);
                                }
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    responses.add(executeThread.getResponse());
                }

                forkJoinPool.execute(messenger.new ResponseSender(responses));
            }
        }
    }
}
