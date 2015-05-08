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
package och.front.web.servlet.system_api.user;

import och.api.model.BaseBean;
import och.api.model.ValidationProcess;
import och.api.model.user.User;

public class AddUserReq extends BaseBean {
	
	public String email;
	public String login;
	public String psw;
	public String captchaChallenge;
	public String captchaResponse;

	@Override
	protected void checkState(ValidationProcess v) {
		v.checkForText(email, "email");
		v.checkForText(login, "login");
		v.checkForText(psw, "psw");
		v.checkForText(captchaChallenge, "captchaChallenge");
		v.checkForText(captchaResponse, "captchaResponse");
	}
	
	public User getUser(){
		return new User(login, email);
	}

}
