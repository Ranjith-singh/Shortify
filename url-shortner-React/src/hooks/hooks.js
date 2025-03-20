import { useQuery } from "@tanstack/react-query";
import api from "../api/api";

export const useFetchTotalClicks = (token, onError) => {
  const startDate = "2025-02-01";
  const endDate= new Date().toISOString().split('T')[0];

  return useQuery({
    queryKey: ["my-shortUrls", startDate, endDate], // Unique query key
    queryFn: async () => {
      const response = await api.get(
        `/api/urls/totalClicks?startDate=${startDate}&endDate=${endDate}`,
        {
          headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
            Authorization: "Bearer " + token,
          },
        }
      );
      return response.data; // Return only data
    },
    select: (data) => {
      return Object.keys(data).map((key) => ({
        clickDate: key,
        count: data[key],
      }));
    },
    onError,
    staleTime: 5000,
  });
};

export const useFetchShortUrls = (token, onError) => {
  return useQuery({
    queryKey: ["my-shortUrls"], // Unique query key
    queryFn: async () => {
      const response = await api.get(
        "api/urls/myUrls",
        {
          headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
            Authorization: "Bearer " + token,
          },
        }
      );
      return response.data; // Return only data
    },
    select: (data) => {
      const sortedData = data.sort(
          (a, b) => new Date(b.createdDate) - new Date(a.createdDate)
      );
      return sortedData;
  },

    onError,
    staleTime: 5000,
  });
};
