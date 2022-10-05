select
	c.Name_En AS Country,
	TRIM(t.TypeName) AS Type,
	m.Name_en AS Mountain,
	m.BestDisplayPoint.STY AS Latitude,
	m.BestDisplayPoint.STX AS Longitude,
	pb.PropertyValue AS Elevation
from
	[dbo].[Entities] c
	join [dbo].[GeoChain] gc on gc.RelateToEntityId = c.EntityId
	join [dbo].[Entities] m on m.EntityId = gc.EntityId
	join [dbo].[LkpTypes] t on t.TypeId = m.TypeId
	join [dbo].[PropertyBag] pb on pb.EntityId = m.EntityId
	join [dbo].[LkpProperties] p on p.PropertyId = pb.PropertyId
where
    --m.EntityId = 5zMDP_7419866682933379073 --Everest
	c.TypeId = 40
	and m.Name_en is not null
	and m.TypeId in (70, 107, 108, 181)
	
