package com.linkpulse.urlservice.link.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class Base62Test {
    @Test 
    void encodeKnownValues(){
        assertThat(Base62.encode(0)).isEqualTo("0");
        assertThat(Base62.encode(9)).isEqualTo("9");
        assertThat(Base62.encode(10)).isEqualTo("A");
        assertThat(Base62.encode(61)).isEqualTo("z");
        assertThat(Base62.encode(62)).isEqualTo("10");
        assertThat(Base62.encode(238_328)).isEqualTo("1000");
    }

    @Test
    void decodeReverseEncode(){
        long[] values = {0, 1, 61, 62, 3666, 238_328, 987_654_321L, Long.MAX_VALUE};
        for(long value: values){
            assertThat(Base62.decode(Base62.encode(value))).isEqualTo(value);
        }
    }

    @Test
    void rejectNegativeNumbers(){
        assertThatThrownBy(() -> Base62.encode(-1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test 
    void rejectInvalidCharacters(){
        assertThatThrownBy(() -> Base62.decode("ab-c")).isInstanceOf(IllegalArgumentException.class);
    }
}
