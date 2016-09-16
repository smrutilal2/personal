package home.thread;

public class ReaderWriter {

    private class Reader extends Thread {

        private String name;
        private SharedData sharedData;

        public Reader(String name, SharedData sharedData) {
            this.name = name;
            this.sharedData = sharedData;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                int value = sharedData.read();
                System.out.println(name + " read " + value);
            }
        }

    }

    private class Writer extends Thread {

        private SharedData sharedData;
        private String name;

        public Writer(String name, SharedData sharedData) {
            this.name = name;
            this.sharedData = sharedData;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                sharedData.write(i);
                System.out.println(name + " wrote " + i);
            }
        }

    }

    private class SharedData {

        private int data;

        private boolean readerPresent = false;
        private boolean writerPresent = false;

        public synchronized void write(int data) {

            while (writerPresent || readerPresent) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            writerPresent = true;
            this.data = data;
            writerPresent = false;
            notifyAll();
        }

        public int read() {
            while (writerPresent) {
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            readerPresent = true;
            int value = this.data;
            readerPresent = false;
            
            synchronized (this) {
                notifyAll();
            }
            return value;
        }

    }
    
    public static void main(String[] args) {
        
        ReaderWriter readerWriter = new ReaderWriter();
        
        SharedData sharedData = readerWriter.new SharedData();
        for (int i = 1; i <= 5; i++) {
            Reader reader = readerWriter.new Reader(String.valueOf(i), sharedData);
            Writer writer = readerWriter.new Writer(String.valueOf(i), sharedData);
            
            reader.start();
            writer.start();
        }
        
    }
}
