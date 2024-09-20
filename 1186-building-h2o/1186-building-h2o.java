import java.util.concurrent.locks.*;
import java.util.concurrent.atomic.*;

class H2O {

    public H2O() {

    }

    private Semaphore hydroWait = new Semaphore(2);
    private Semaphore oxyWait = new Semaphore(1);
    private CyclicBarrier barrier = new CyclicBarrier(3);

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hydroWait.acquire();
        try {
            barrier.await();
            releaseHydrogen.run();

        } catch (BrokenBarrierException e) {
        } finally {
            hydroWait.release();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oxyWait.acquire();
        try {
            barrier.await();
            releaseOxygen.run();
        } catch (BrokenBarrierException e) {
        } finally {
            oxyWait.release();
        }
    }
}