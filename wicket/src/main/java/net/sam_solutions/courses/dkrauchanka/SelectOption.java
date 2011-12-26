package net.sam_solutions.courses.dkrauchanka;

public class SelectOption{
        private String key;
        private String value;
        public SelectOption(String key, String value){
            this.key = key;
            this.value = value;
        }
        public String getKey(){
            return key;
        }
        public void setKey(String key){
            this.key = key;
        }
        public String getValue(){
            return value;
        }
        public void setValue(String value){
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final SelectOption other = (SelectOption) obj;
            if ((this.key == null) ? (other.key != null) : !this.key.equals(other.key)) {
                return false;
            }
            if ((this.value == null) ? (other.value != null) : !this.value.equals(other.value)) {
                return false;
            }
            return true;
        }
    }