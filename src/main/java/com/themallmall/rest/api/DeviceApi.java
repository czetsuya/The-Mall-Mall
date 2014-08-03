package com.themallmall.rest.api;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.themallmall.exception.BusinessException;
import com.themallmall.model.Device;
import com.themallmall.model.crm.User;
import com.themallmall.service.DeviceService;
import com.themallmall.service.UserService;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class DeviceApi {

	@Inject
	private EntityManager em;

	@Inject
	private DeviceService deviceService;

	@Inject
	private UserService userService;

	public void addDevice(String deviceToken, String userId) {
		User user = userService.findById(Long.parseLong(userId));
		if (user != null) {
			if (deviceService.findByToken(em, deviceToken) == null) {
				Device device = new Device();
				device.setDeviceToken(deviceToken);
				device.setUser(user);

				try {
					deviceService.addDevice(em, device);
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
