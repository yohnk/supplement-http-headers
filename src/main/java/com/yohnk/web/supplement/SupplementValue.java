package com.yohnk.web.supplement;

/**
 * Created by ryanyohnk on 3/7/17.
 */
class SupplementValue {
    private String header;
    private String value;

    public SupplementValue(String header, String value) {
        this.header = header;
        this.value = value;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SupplementValue that = (SupplementValue) o;

        if (header != null ? !header.equals(that.header) : that.header != null) return false;
        return value != null ? value.equals(that.value) : that.value == null;

    }

    @Override
    public int hashCode() {
        int result = header != null ? header.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
