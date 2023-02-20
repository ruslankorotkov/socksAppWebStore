package sky.pro.socksappwebstore.model;

public enum Size {
        S(36.0,37.5),
        M(38.0,40.0),
        L(41.0,43.0),
        XL(44.0,46.0),
        XXL(47.0,48.0);
        private final Double maxSize;
        private final Double minSize;

        public Double getMaxSize() {
            return maxSize;
        }

        public Double getMinSize() {
            return minSize;
        }

        Size(Double maxSize, Double minSize) {
            this.maxSize = maxSize;
            this.minSize = minSize;
        }

        @Override
        public String toString() {
            return "Size{" +
                    "maxSize=" + maxSize +
                    ", minSize=" + minSize +
                    '}';
        }
    }