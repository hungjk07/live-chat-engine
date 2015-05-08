/*
 * Copyright 2015 Evgeny Dolganov (evgenij.dolganov@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package och.api.model.tariff;

import java.math.BigDecimal;

public class Tariff implements Cloneable {
	
	
	public static final long PAUSE_TARIFF_ID = 0L;
	
	public long id;
	public BigDecimal price;
	public boolean isPublic;
	public int maxOperators;
	
	public Tariff() {
		super();
	}
	
	public Tariff(long id, BigDecimal price, boolean isPublic, int maxOperators) {
		super();
		this.id = id;
		this.price = price;
		this.isPublic = isPublic;
		this.maxOperators = maxOperators;
	}
	
	@Override
	public Tariff clone() {
		try {
			return (Tariff)super.clone();
		}catch(Exception e){
			throw new IllegalStateException(e);
		}
	}
	

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public boolean isPublic() {
		return isPublic;
	}
	
	public void setIsPublic(boolean isPublic) {
		setPublic(isPublic);
	}
	
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public int getMaxOperators() {
		return maxOperators;
	}

	public void setMaxOperators(int maxOperators) {
		this.maxOperators = maxOperators > -1? maxOperators : 0;
	}
	
	

}
